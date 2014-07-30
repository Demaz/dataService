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
import org.springframework.util.StringUtils;

import seo.scanner.domain.Projet;
import seo.scanner.domain.ProjetListUrl;
import seo.scanner.domain.UrlToCheck;
import seo.scanner.domain.User;

public class ProjetService {
	
	@Autowired
	private DataSource dataSource;

	private JdbcTemplate jdbcTemplate;
	
	public JdbcTemplate getJdbcTemplate() {
		
		if(jdbcTemplate == null) {
			jdbcTemplate = new JdbcTemplate(this.dataSource);
		}
		return jdbcTemplate;
	}
	
	public Projet addProjet(final Projet projet) {
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement("insert into tprojets(name,description,domaine) values(?,?,?)", new String[] {"uid"});
			            ps.setString(1, projet.getName());
			            ps.setString(2, projet.getDescription());
			            ps.setString(3, projet.getDomaine());
			            return ps;
			        }
			    },
			    keyHolder);
		projet.setUid(keyHolder.getKey().intValue());
		
		getJdbcTemplate().update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement("insert into tprojetsusers(projetUid,userUid) values(?,?)", new String[] {"uid"});
			            ps.setInt(1, projet.getUid());
			            ps.setInt(2, projet.getUserUid());
			            return ps;
			        }
			    },
			    keyHolder);
		return projet;
	}
	
	public List<Projet> getProjetsByUserUid(Integer userUid) {
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList("Select uid, name, description , domaine from tprojets p where uid in (Select pu.projetUid from tprojetsusers pu where pu.userUid = ?)",userUid);
		List<Projet> ProjetList = new ArrayList<Projet>();
		for (Map<String,Object> row : rows) {
			ProjetList.add(mapProjetWithResultSet(row));
		}
		return ProjetList;
		
	}
	
	public Projet getProjetbyUid(Integer projetUid,Integer userUid) {
		List<Map<String, Object>> rows = getJdbcTemplate().queryForList("Select distinct p.uid, p.name, p.description , p.domaine from tprojets p where p.uid = ? and uid in (Select pu.projetUid from tprojetsusers pu where pu.userUid = ?)",projetUid,userUid);
		return mapProjetWithResultSet(rows.get(0));
	}
	
	public List<ProjetListUrl> getProjetUrlListes(Integer userUid,Integer projetUid) {
		List<ProjetListUrl> projetListUrlListe = new ArrayList<ProjetListUrl>();
		List<Map<String, Object>> rowsProjetListUrl = getJdbcTemplate().queryForList("Select plu.uid, plu.projetUid, plu.name, plu.description from tprojetsListUrl plu inner join tprojets p on plu.projetUid = p.uid where plu.projetUid = ? and p.uid in (select projetUid from tprojetsusers pu where pu.userUid = ?)",projetUid, userUid);
		for(Map<String, Object> row : rowsProjetListUrl) {
			projetListUrlListe.add(mapProjetListUrlWithResultSet(row));
		}
		return projetListUrlListe;
	}
	
	public ProjetListUrl getProjetListUrl(Integer userUid,Integer projetUid, Integer projetListUrlUid) {
		ProjetListUrl projetListUrl = new ProjetListUrl();
		List<Map<String, Object>> rowsProjetListUrl = getJdbcTemplate().queryForList("Select plu.uid, plu.projetUid, plu.name, plu.description from tprojetsListUrl plu inner join tprojets p on plu.projetUid = p.uid where plu.projetUid = ? and p.uid in (Select projetUid from tprojetsusers pu where pu.userUid = ?) and plu.uid = ?",projetUid, userUid,projetListUrlUid);
		if(rowsProjetListUrl.size() > 0) {
		projetListUrl = mapProjetListUrlWithResultSet(rowsProjetListUrl.get(0));
				List<Map<String, Object>> rowsUrlToCheck = getJdbcTemplate().queryForList("Select uid, url,redirectionUrl1,redirectionUrlCode1,redirectionUrl2,redirectionUrlCode2,redirectionUrl3,redirectionUrlCode3,projetListUrlUid from turltocheck where projetlisturluid = ?",projetListUrl.getUid());
				projetListUrl.setUrlToCheckList(mapUrlToCheckWithResultSet(rowsUrlToCheck));
		}
		return projetListUrl;
	}
	
	private List<UrlToCheck> mapUrlToCheckWithResultSet(List<Map<String, Object>> rows) {
		List<UrlToCheck> urlToCheckList= new ArrayList<UrlToCheck>();
		for(Map<String, Object> row : rows) {
			UrlToCheck urlToCheck = new UrlToCheck();
			urlToCheck.setUid(Integer.valueOf(row.get("uid").toString()));
			urlToCheck.setProjetListUrlUid(Integer.valueOf(row.get("projetListUrlUid").toString()));
			urlToCheck.setUrl(getRowStringValue(row,"url"));
			urlToCheck.setRedirectionUrl1(getRowStringValue(row,"redirectionUrl1"));
			urlToCheck.setRedirectionUrlCode1(getRowStringValue(row,"redirectionUrlCode1"));
			urlToCheck.setRedirectionUrl2(getRowStringValue(row,"redirectionUrl2"));
			urlToCheck.setRedirectionUrlCode2(getRowStringValue(row,"redirectionUrlCode2"));
			urlToCheck.setRedirectionUrl3(getRowStringValue(row,"redirectionUrl3"));
			urlToCheck.setRedirectionUrlCode3(getRowStringValue(row,"redirectionUrlCode3"));
			urlToCheckList.add(urlToCheck);
		}
		return urlToCheckList;
	}
	
	private String getRowStringValue(Map<String, Object> row, String key) {
		if(row.get(key) != null) {
			return row.get(key).toString();
		}
		return "";
	}
	
	private ProjetListUrl mapProjetListUrlWithResultSet(Map<String,Object> row) {
		ProjetListUrl projetListUrl = new ProjetListUrl();
		projetListUrl.setUid(Integer.valueOf(row.get("uid").toString()));
		projetListUrl.setProjetUid(Integer.valueOf(row.get("projetUid").toString()));
		projetListUrl.setName(row.get("name").toString());
		projetListUrl.setDescription(row.get("description").toString());
		return projetListUrl;
		
	}
	
	private Projet mapProjetWithResultSet(Map<String,Object> row) {
		Projet projet = new Projet();
		projet.setUid(Integer.valueOf(row.get("uid").toString()));
		projet.setName(row.get("name").toString());
		projet.setDescription(row.get("description").toString());
		projet.setDomaine((row.get("domaine").toString()));
		return projet;
	}

	public List<User> getProjetUsers(Integer userUid, Integer projetUid) {
		List<User> users = new ArrayList<User>();
		List<Map<String, Object>> rowsUsers = getJdbcTemplate().queryForList("Select u.name from tprojetsusers as pu inner join tusers as u on pu.userUid = u.uid inner join tprojets p on pu.projetUid = p.uid where p.uid = ?",projetUid);
			for(Map<String, Object> row : rowsUsers) {
				User user = new User();
				user.setName(row.get("name").toString());
				users.add(user);
			}
	    return users;
	}

	public ProjetListUrl addListUrl(final ProjetListUrl projetListUrl,final Integer userUid,final Integer projetUid) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement("insert into tprojetslisturl(projetuid,name,description) values(?,?,?)", new String[] {"uid"});
			            ps.setInt(1, projetUid);
			            ps.setString(2, projetListUrl.getName());
			            ps.setString(3, projetListUrl.getDescription());
			            return ps;
			        }
			    },
			    keyHolder);
		projetListUrl.setUid(keyHolder.getKey().intValue());
		
		return projetListUrl;
	}

	public UrlToCheck addUrlToList(final UrlToCheck urlToCheck) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		getJdbcTemplate().update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement("insert into turltocheck(url,redirectionUrl1,redirectionUrlCode1,redirectionUrl2,redirectionUrlCode2,redirectionUrl3,redirectionUrlCode3,projetListUrlUid) values(?,?,?,?,?,?,?,?)", new String[] {"uid"});
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
			    },
			    keyHolder);
		urlToCheck.setUid(keyHolder.getKey().intValue());
		
		return urlToCheck;
	}

	public UrlToCheck updateUrlInList(final UrlToCheck urlToCheck) {
		getJdbcTemplate().update(
			    new PreparedStatementCreator() {
			        public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
			            PreparedStatement ps =
			                connection.prepareStatement("update turltocheck set url = ?,redirectionUrl1 = ?,redirectionUrlCode1 = ?,redirectionUrl2 = ?,redirectionUrlCode2 = ?,redirectionUrl3 = ?,redirectionUrlCode3 = ? where uid = ?");
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
	
	public void deleteUrlInList(Integer uid,Integer userUid) {
		getJdbcTemplate().update("delete from turltocheck where uid = ? and projetListUrlUid in (Select plu.uid from tprojetslisturl plu inner join tprojets p on plu.projetuid = p.uid inner join tprojetsusers pu on p.uid = pu.projetuid where useruid = ?)",uid,userUid);
	}
}