package seo.scanner.dataService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import seo.scanner.domain.Alertes;
import seo.scanner.domain.Event;
import seo.scanner.domain.Parameters;
import seo.scanner.domain.Projet;
import seo.scanner.domain.ProjetListUrl;
import seo.scanner.domain.UrlCheckResult;
import seo.scanner.domain.UrlToCheck;
import seo.scanner.domain.User;
import seo.scanner.domain.Useragent;

public class ProjetService {

	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;

	public UrlToCheck addCheckResult(final UrlCheckResult urlCheckResult) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into tprojetslisturlresults(url,redirectionUrl1,redirectionUrlCode1,redirectionUrl2,redirectionUrlCode2,redirectionUrl3,redirectionUrlCode3,projetListUrlUid,eventUid) values(?,?,?,?,?,?,?,?,?)",
						new String[] { "uid" });
				ps.setString(1, urlCheckResult.getUrl());
				ps.setString(2, urlCheckResult.getRedirectionUrl1());
				ps.setString(3, urlCheckResult.getRedirectionUrlCode1());
				ps.setString(4, urlCheckResult.getRedirectionUrl2());
				ps.setString(5, urlCheckResult.getRedirectionUrlCode2());
				ps.setString(6, urlCheckResult.getRedirectionUrl3());
				ps.setString(7, urlCheckResult.getRedirectionUrlCode3());
				ps.setInt(8, urlCheckResult.getProjetListUrlUid());
				ps.setInt(9, urlCheckResult.getEventUid());
				return ps;
			}
		}, keyHolder);
		urlCheckResult.setUid(keyHolder.getKey().intValue());

		return urlCheckResult;
	}

	public ProjetListUrl addListUrl(final ProjetListUrl projetListUrl, final Integer userUid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into tprojetslisturl(projetuid,name,description,typeUid) values(?,?,?,?)",
						new String[] { "uid" });
				ps.setInt(1, projetListUrl.getProjetUid());
				ps.setString(2, projetListUrl.getName());
				ps.setString(3, projetListUrl.getDescription());
				ps.setInt(4, projetListUrl.getTypeUid());
				return ps;
			}
		}, keyHolder);
		projetListUrl.setUid(keyHolder.getKey().intValue());

		return projetListUrl;
	}

	public Projet addProjet(final Projet projet) {

		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into tprojets(name,description,domaine) values(?,?,?)", new String[] { "uid" });
				ps.setString(1, projet.getName());
				ps.setString(2, projet.getDescription());
				ps.setString(3, projet.getDomaine());
				return ps;
			}
		}, keyHolder);
		projet.setUid(keyHolder.getKey().intValue());

		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into tprojetsusers(projetUid,userUid) values(?,?)", new String[] { "uid" });
				ps.setInt(1, projet.getUid());
				ps.setInt(2, projet.getUserUid());
				return ps;
			}
		}, keyHolder);
		return projet;
	}

	public Integer addProjetUser(final Integer userUid, final Integer projetUid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into tprojetsusers(userUid,projetUid) values(?,?)", new String[] { "uid" });
				ps.setInt(1, userUid);
				ps.setInt(2, projetUid);
				return ps;
			}
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}

	public List<UrlToCheck> addUrlsToList(List<UrlToCheck> urlToChecks, Integer projetListUrlUid) {
		for (UrlToCheck urlToCheck : urlToChecks) {
			urlToCheck.setProjetListUrlUid(projetListUrlUid);
			addUrlToList(urlToCheck);
		}

		return urlToChecks;
	}

	public UrlToCheck addUrlToList(final UrlToCheck urlToCheck) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection.prepareStatement(
						"insert into turltocheck(url,redirectionUrl1,redirectionUrlCode1,redirectionUrl2,redirectionUrlCode2,redirectionUrl3,redirectionUrlCode3,projetListUrlUid) values(?,?,?,?,?,?,?,?)",
						new String[] { "uid" });
				ps.setString(1, urlToCheck.getUrl());
				ps.setString(2, urlToCheck.getRedirectionUrl1());
				ps.setString(3, urlToCheck.getRedirectionUrlCode1());
				ps.setString(4, urlToCheck.getRedirectionUrl2());
				ps.setString(5, urlToCheck.getRedirectionUrlCode2());
				ps.setString(6, urlToCheck.getRedirectionUrl3());
				ps.setString(7, urlToCheck.getRedirectionUrlCode3());
				ps.setInt(8, urlToCheck.getProjetListUrlUid());
				return ps;
			}
		}, keyHolder);
		urlToCheck.setUid(keyHolder.getKey().intValue());

		return urlToCheck;
	}

	public List<UrlToCheck> addUrslToList(List<UrlToCheck> urlToChecks) {
		for (UrlToCheck urlToCheck : urlToChecks) {
			addUrlToList(urlToCheck);
		}

		return urlToChecks;
	}

	public void cleanProjetListUrl(Integer projetListUrlUid) {
		getJdbcTemplate().update("delete from turltocheck where projetListUrlUid = ?", projetListUrlUid);
	}

	public void deleteUrlInList(Integer uid, Integer userUid) {
		getJdbcTemplate()
				.update("delete from turltocheck where uid = ? and projetListUrlUid in (Select plu.uid from tprojetslisturl plu inner join tprojets p on plu.projetuid = p.uid inner join tprojetsusers pu on p.uid = pu.projetuid where useruid = ?)",
						uid, userUid);
	}

	public void flagEventEnd(Event event) {
		getJdbcTemplate()
				.update("update tprojetslisturlevents set isInProgress = false , endDate = now() where uid = ? and projetListUrlUid = ?",
						event.getUid(), event.getProjetListUrlUid());
	}

	public void flagEventStart(Event event) {
		getJdbcTemplate()
				.update("update tprojetslisturlevents set isInProgress = true , startDate = now() where uid = ? and projetListUrlUid = ?",
						event.getUid(), event.getProjetListUrlUid());
	}

	public Alertes getAlertes(Integer projetListUrlUid) {
		Alertes alertes = new Alertes();
		List<Map<String, Object>> rowsUsers = getJdbcTemplate().queryForList(
				"Select uid, name, description, type, pourcentage  from tprojetslisturlalertes");
		for (Map<String, Object> row : rowsUsers) {

			alertes.setUid(Integer.valueOf(row.get("uid").toString()));
			alertes.setName(row.get("name").toString());
			alertes.setDescription(row.get("description").toString());
			alertes.setType(row.get("type").toString());
			alertes.setPourcentage(Integer.valueOf(row.get("pourcentage").toString()));

		}
		return alertes;
	}

	public List<Event> getAllEventsToDo() {
		List<Map<String, Object>> rows = getJdbcTemplate()
				.queryForList(
						"Select uid, projetlisturluid from tprojetslisturlevents where isInProgress = false and startDate is null and endDate is null");
		List<Event> events = new ArrayList<Event>();
		for (Map row : rows) {
			Event event = new Event();
			event.setUid((Integer) row.get("uid"));
			event.setProjetListUrlUid((Integer) row.get("projetlisturluid"));
			events.add(event);
		}
		return events;
	}

	public List<User> getComptesUsers(Integer projetUid, Integer userUid) {
		List<User> users = new ArrayList<User>();
		List<Map<String, Object>> rowsUsers = getJdbcTemplate()
				.queryForList(
						"Select uid, name , username , email from tusers where uid not in (Select userUid from tprojetsusers where projetUid = ?) and compteuid in (Select c.uid from tusers as u inner join tcomptes as c on u.compteUid = c.uid where u.uid = ?)",
						projetUid, userUid);
		for (Map<String, Object> row : rowsUsers) {
			User user = new User();
			user.setName(row.get("name").toString());
			user.setUid(Integer.valueOf(row.get("uid").toString()));
			users.add(user);
		}
		return users;
	}

	public List<UrlCheckResult> getCrawResults(final Integer eventStartUid, final Integer eventEndUid,
			Integer projetListUrlUid) {
		List<Map<String, Object>> rows = getJdbcTemplate()
				.queryForList(
						"Select uid, url, eventUid,redirectionUrl1,redirectionUrlCode1,redirectionUrl2,redirectionUrlCode2,redirectionUrl3,redirectionUrlCode3,checkDate,projetListUrlUid from tprojetslisturlresults where eventUid >= ? and eventUid <= ? and projetlisturluid = ? order by uid desc limit 100",
						eventStartUid, eventEndUid, projetListUrlUid);
		return mapUrlCheckResultWithResultSet(rows);
	}

	public List<Event> getEventsDoneOfUrlList(Integer projetListUrlUid) {
		List<Map<String, Object>> rows = getJdbcTemplate()
				.queryForList(
						"Select uid, projetlisturluid , createdate , startdate , isinprogress , enddate from tprojetslisturlevents where startDate is not null and endDate is not null and isInProgress = false and projetListUrlUid = ?",
						projetListUrlUid);
		return mapEventResultSet(rows);
	}

	public List<Event> getEventsToDoOfUrlList(Integer projetListUrlUid) {
		List<Map<String, Object>> rows = getJdbcTemplate()
				.queryForList(
						"Select uid, projetlisturluid , createdate , startdate , isinprogress , enddate from tprojetslisturlevents where startDate is null and isInProgress = false and projetListUrlUid = ?",
						projetListUrlUid);
		return mapEventResultSet(rows);
	}

	public JdbcTemplate getJdbcTemplate() {

		if (jdbcTemplate == null) {
			jdbcTemplate = new JdbcTemplate(this.dataSource);
		}
		return jdbcTemplate;
	}

	public List<Event> getLastEventDoneOfUrlList(Integer projetListUrlUid) {
		List<Map<String, Object>> rows = getJdbcTemplate()
				.queryForList(
						"Select uid, projetlisturluid , createdate , startdate , isinprogress , enddate from tprojetslisturlevents where startDate is not null and endDate is not null and isInProgress = false and projetListUrlUid = ? order by endDate desc",
						projetListUrlUid);
		return mapEventResultSet(rows);
	}

	public Parameters getParameters(Integer projetListUrlUid) {
		Parameters parameters = new Parameters();
		List<Map<String, Object>> rowsUsers = getJdbcTemplate()
				.queryForList(
						"Select uid, useragentuid , projetListUrlUid , jourMois, jour, heure, minute, frequence  from tprojetslisturlparameters");
		for (Map<String, Object> row : rowsUsers) {

			parameters.setUserAgentUid(Integer.valueOf(row.get("useragentuid").toString()));
			parameters.setProjetListUrlUid(Integer.valueOf(row.get("projetListUrlUid").toString()));
			parameters.setUid(Integer.valueOf(row.get("uid").toString()));
			parameters.setJourMois(Integer.valueOf(row.get("jourMois").toString()));
			parameters.setJour(Integer.valueOf(row.get("jour").toString()));
			parameters.setHeure(Integer.valueOf(row.get("heure").toString()));
			parameters.setMinute(Integer.valueOf(row.get("minute").toString()));
			parameters.setFrequence(row.get("frequence").toString());

		}

		return parameters;
	}

	public Projet getProjetbyUid(Integer projetUid, Integer userUid) {
		List<Map<String, Object>> rows = getJdbcTemplate()
				.queryForList(
						"Select distinct p.uid, p.name, p.description , p.domaine from tprojets p where p.uid = ? and uid in (Select pu.projetUid from tprojetsusers pu where pu.userUid = ?)",
						projetUid, userUid);
		return mapProjetWithResultSet(rows.get(0));
	}

	public ProjetListUrl getProjetListUrl(Integer userUid, Integer projetUid, Integer projetListUrlUid,
			Boolean loadEventsTodo, Boolean loadEventsDone) {
		ProjetListUrl projetListUrl = new ProjetListUrl();
		List<Map<String, Object>> rowsProjetListUrl = getJdbcTemplate()
				.queryForList(
						"Select plu.uid, plu.projetUid, plu.name, plu.description , (Select count(*) from turltocheck where projetlisturluid = plu.uid) as nburl from tprojetsListUrl plu inner join tprojets p on plu.projetUid = p.uid where plu.projetUid = ? and p.uid in (Select projetUid from tprojetsusers pu where pu.userUid = ?) and plu.uid = ?",
						projetUid, userUid, projetListUrlUid);
		if (rowsProjetListUrl.size() > 0) {
			projetListUrl = mapProjetListUrlWithResultSet(rowsProjetListUrl.get(0));
			if (loadEventsTodo) {
				projetListUrl.setEventsToDo(getEventsToDoOfUrlList(projetListUrlUid));
			}
			if (loadEventsDone) {
				projetListUrl.setEventsDone(getEventsDoneOfUrlList(projetListUrlUid));
			}
			List<Map<String, Object>> rowsUrlToCheck = getJdbcTemplate()
					.queryForList(
							"Select uid, url,redirectionUrl1,redirectionUrlCode1,redirectionUrl2,redirectionUrlCode2,redirectionUrl3,redirectionUrlCode3,projetListUrlUid from turltocheck where projetlisturluid = ? limit 250",
							projetListUrl.getUid());
			projetListUrl.setUrlToCheckList(mapUrlToCheckWithResultSet(rowsUrlToCheck));
		}
		return projetListUrl;
	}

	public List<Projet> getProjetsByUserUid(Integer userUid) {
		List<Map<String, Object>> rows = getJdbcTemplate()
				.queryForList(
						"Select uid, name, description , domaine from tprojets p where uid in (Select pu.projetUid from tprojetsusers pu where pu.userUid = ?)",
						userUid);
		List<Projet> ProjetList = new ArrayList<Projet>();
		for (Map<String, Object> row : rows) {
			ProjetList.add(mapProjetWithResultSet(row));
		}
		return ProjetList;

	}

	public List<ProjetListUrl> getProjetUrlListes(Integer userUid, Integer projetUid) {
		List<ProjetListUrl> projetListUrlListe = new ArrayList<ProjetListUrl>();
		List<Map<String, Object>> rowsProjetListUrl = getJdbcTemplate()
				.queryForList(
						"Select plu.uid, plu.projetUid, plu.name, plu.description , (Select count(*) from turltocheck where projetlisturluid = plu.uid) as nburl from tprojetsListUrl plu inner join tprojets p on plu.projetUid = p.uid where plu.projetUid = ? and p.uid in (select projetUid from tprojetsusers pu where pu.userUid = ?)",
						projetUid, userUid);
		for (Map<String, Object> row : rowsProjetListUrl) {
			projetListUrlListe.add(mapProjetListUrlWithResultSet(row));
		}
		return projetListUrlListe;
	}

	public List<User> getProjetUsers(Integer userUid, Integer projetUid) {
		List<User> users = new ArrayList<User>();
		List<Map<String, Object>> rowsUsers = getJdbcTemplate()
				.queryForList(
						"Select u.name from tprojetsusers as pu inner join tusers as u on pu.userUid = u.uid inner join tprojets p on pu.projetUid = p.uid where p.uid = ?",
						projetUid);
		for (Map<String, Object> row : rowsUsers) {
			User user = new User();
			user.setName(row.get("name").toString());
			users.add(user);
		}
		return users;
	}

	private String getRowStringValue(Map<String, Object> row, String key) {
		if (row.get(key) != null) {
			return row.get(key).toString();
		}
		return "";
	}

	public List<UrlCheckResult> getUrlToCheck(Integer projetListUrlUid) {
		List<Map<String, Object>> rowsUrlToCheck = getJdbcTemplate()
				.queryForList(
						"Select uid, url,redirectionUrl1,redirectionUrlCode1,redirectionUrl2,redirectionUrlCode2,redirectionUrl3,redirectionUrlCode3,projetListUrlUid from turltocheck where projetlisturluid = ?",
						projetListUrlUid);
		return mapUrlCheckResultWithResultSet(rowsUrlToCheck);
	}

	public List<Useragent> getUseragents() {
		List<Useragent> useragents = new ArrayList<Useragent>();
		List<Map<String, Object>> rowsUsers = getJdbcTemplate().queryForList(
				"Select name , useragent, uid from tuseragents");
		for (Map<String, Object> row : rowsUsers) {
			Useragent useragent = new Useragent();
			useragent.setName(row.get("name").toString());
			useragent.setUseragent(row.get("useragent").toString());
			useragent.setUid(Integer.valueOf(row.get("uid").toString()));
			useragents.add(useragent);
		}
		return useragents;
	}

	private List<Event> mapEventResultSet(List<Map<String, Object>> rows) {

		List<Event> events = new ArrayList<Event>();
		for (Map row : rows) {
			Event event = new Event();
			event.setUid((Integer) row.get("uid"));
			event.setProjetListUrlUid((Integer) row.get("projetlisturluid"));

			event.setCreateDate((Date) row.get("createdate"));
			event.setStartDate((Date) row.get("startdate"));
			event.setIsInProgress((Boolean) row.get("isinprogress"));
			event.setEndDate((Date) row.get("enddate"));

			events.add(event);
		}

		return events;
	}

	private ProjetListUrl mapProjetListUrlWithResultSet(Map<String, Object> row) {
		ProjetListUrl projetListUrl = new ProjetListUrl();
		projetListUrl.setUid(Integer.valueOf(row.get("uid").toString()));
		projetListUrl.setProjetUid(Integer.valueOf(row.get("projetUid").toString()));
		projetListUrl.setName(row.get("name").toString());
		projetListUrl.setDescription(row.get("description").toString());
		projetListUrl.setNbUrl(Integer.valueOf(row.get("nburl").toString()));
		return projetListUrl;

	}

	private Projet mapProjetWithResultSet(Map<String, Object> row) {
		Projet projet = new Projet();
		projet.setUid(Integer.valueOf(row.get("uid").toString()));
		projet.setName(row.get("name").toString());
		projet.setDescription(row.get("description").toString());
		projet.setDomaine((row.get("domaine").toString()));
		return projet;
	}

	private List<UrlCheckResult> mapUrlCheckResultWithResultSet(List<Map<String, Object>> rows) {
		List<UrlCheckResult> urlCheckResults = new ArrayList<UrlCheckResult>();
		for (Map<String, Object> row : rows) {
			UrlCheckResult urlCheckResult = new UrlCheckResult();
			urlCheckResult.setUid(Integer.valueOf(row.get("uid").toString()));
			urlCheckResult.setProjetListUrlUid(Integer.valueOf(row.get("projetListUrlUid").toString()));
			urlCheckResult.setUrl(getRowStringValue(row, "url"));
			urlCheckResult.setRedirectionUrl1(getRowStringValue(row, "redirectionUrl1"));
			urlCheckResult.setRedirectionUrlCode1(getRowStringValue(row, "redirectionUrlCode1"));
			urlCheckResult.setRedirectionUrl2(getRowStringValue(row, "redirectionUrl2"));
			urlCheckResult.setRedirectionUrlCode2(getRowStringValue(row, "redirectionUrlCode2"));
			urlCheckResult.setRedirectionUrl3(getRowStringValue(row, "redirectionUrl3"));
			urlCheckResult.setRedirectionUrlCode3(getRowStringValue(row, "redirectionUrlCode3"));

			if (row.containsKey("eventUid")) {
				urlCheckResult.setEventUid(Integer.valueOf(row.get("eventUid").toString()));
				urlCheckResult.setCheckDate((Date) row.get("checkDate"));
			}

			urlCheckResults.add(urlCheckResult);
		}
		return urlCheckResults;
	}

	private List<UrlToCheck> mapUrlToCheckWithResultSet(List<Map<String, Object>> rows) {
		List<UrlToCheck> urlToCheckList = new ArrayList<UrlToCheck>();
		for (Map<String, Object> row : rows) {
			UrlToCheck urlToCheck = new UrlToCheck();
			urlToCheck.setUid(Integer.valueOf(row.get("uid").toString()));
			urlToCheck.setProjetListUrlUid(Integer.valueOf(row.get("projetListUrlUid").toString()));
			urlToCheck.setUrl(getRowStringValue(row, "url"));
			urlToCheck.setRedirectionUrl1(getRowStringValue(row, "redirectionUrl1"));
			urlToCheck.setRedirectionUrlCode1(getRowStringValue(row, "redirectionUrlCode1"));
			urlToCheck.setRedirectionUrl2(getRowStringValue(row, "redirectionUrl2"));
			urlToCheck.setRedirectionUrlCode2(getRowStringValue(row, "redirectionUrlCode2"));
			urlToCheck.setRedirectionUrl3(getRowStringValue(row, "redirectionUrl3"));
			urlToCheck.setRedirectionUrlCode3(getRowStringValue(row, "redirectionUrlCode3"));
			urlToCheckList.add(urlToCheck);
		}
		return urlToCheckList;
	}

	public Alertes saveAlertes(final Alertes alertes) {

		final String updateQuery = "update tprojetslisturlalertes set name  = ?, description = ?, type = ?, pourcentage = ? where projetListUrlUid = ? and uid = ?";
		final String insertQuery = "insert into tprojetslisturlalertes(name,description,type,pourcentage,projetListUrlUid) values(?,?,?,?,?)";

		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = null;
				if (alertes.getUid() != null) {
					ps = connection.prepareStatement(updateQuery);
					ps.setInt(8, alertes.getUid());
				} else {
					ps = connection.prepareStatement(insertQuery);
				}
				ps.setString(1, alertes.getName());
				ps.setString(2, alertes.getDescription());
				ps.setString(3, alertes.getType());
				ps.setInt(4, alertes.getPourcentage());
				ps.setInt(5, alertes.getProjetListUrlUid());

				return ps;
			}
		});
		return alertes;
	}

	public Parameters saveParameters(final Parameters parameters) {

		final String updateQuery = "update tprojetslisturlparameters set useragentuid = ?, jourMois = ?, jour = ?, heure = ?, minute = ?, frequence = ? where  projetListUrlUid = ? and uid = ?";
		final String insertQuery = "insert into tprojetslisturlparameters(useragentuid,jourMois,jour,heure,minute,frequence,projetListUrlUid) values(?,?,?,?,?,?,?)";

		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = null;
				if (parameters.getUid() != null) {
					ps = connection.prepareStatement(updateQuery);
					ps.setInt(8, parameters.getUid());
				} else {
					ps = connection.prepareStatement(insertQuery);
				}
				ps.setInt(1, parameters.getUserAgentUid());
				ps.setInt(2, parameters.getJourMois());
				ps.setInt(3, parameters.getJour());
				ps.setInt(4, parameters.getHeure());
				ps.setInt(5, parameters.getMinute());
				ps.setString(6, parameters.getFrequence());
				ps.setInt(7, parameters.getProjetListUrlUid());

				return ps;
			}
		});
		return parameters;
	}

	public Event submitListe(final Event event) {

		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection
						.prepareStatement("insert into tprojetslisturlevents(projetListUrlUid) values(?)");
				ps.setInt(1, event.getProjetListUrlUid());
				return ps;
			}
		});
		return event;
	}

	public UrlToCheck updateUrlInList(final UrlToCheck urlToCheck) {
		getJdbcTemplate().update(new PreparedStatementCreator() {
			public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
				PreparedStatement ps = connection
						.prepareStatement("update turltocheck set url = ?,redirectionUrl1 = ?,redirectionUrlCode1 = ?,redirectionUrl2 = ?,redirectionUrlCode2 = ?,redirectionUrl3 = ?,redirectionUrlCode3 = ? where uid = ?");
				ps.setString(1, urlToCheck.getUrl());
				ps.setString(2, urlToCheck.getRedirectionUrl1());
				ps.setString(3, urlToCheck.getRedirectionUrlCode1());
				ps.setString(4, urlToCheck.getRedirectionUrl2());
				ps.setString(5, urlToCheck.getRedirectionUrlCode2());
				ps.setString(6, urlToCheck.getRedirectionUrl3());
				ps.setString(7, urlToCheck.getRedirectionUrlCode3());
				ps.setInt(8, urlToCheck.getUid());
				return ps;
			}
		});
		return urlToCheck;
	}

}
