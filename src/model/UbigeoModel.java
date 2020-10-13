package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import entidad.Ubigeo;
import util.MySqlDBConexion;

public class UbigeoModel {

	private static final Log log = LogFactory.getLog(UbigeoModel.class);

	public List<Ubigeo> traeDepartamentos(){
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<Ubigeo> lista = new ArrayList<Ubigeo>();
		try {
			String sql = "select  distinct departamento from ubigeo";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			log.info(pstm);
			rs = pstm.executeQuery();
			Ubigeo bean = null;
			while(rs.next()){
				bean = new Ubigeo();
				bean.setDepartamento(rs.getString(1));
				lista.add(bean);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)rs.close();
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {}
		}
		return lista;
	}
	
	public List<Ubigeo> traeProvincias(Ubigeo ubigeoBean) {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<Ubigeo> lista = new ArrayList<Ubigeo>();
		try {
			String sql = 
			"select  distinct provincia from ubigeo where departamento=?";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, ubigeoBean.getIdDepartamento());
			log.info(pstm);
			rs = pstm.executeQuery();
			Ubigeo bean = null;
			while(rs.next()){
				bean = new Ubigeo();
				bean.setProvincia(rs.getString(1));
				lista.add(bean);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)rs.close();
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {}
		}
		return lista;
	}
	
	public List<Ubigeo> traeDistritos(Ubigeo ubigeoBean)  {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		List<Ubigeo> lista = new ArrayList<Ubigeo>();
		try {
			String sql = 
			"select  distinct id,distrito from ubigeo where " +
			" departamento =? and provincia=?";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, ubigeoBean.getIdDepartamento());
			pstm.setString(2, ubigeoBean.getIdProvincia());
			log.info(pstm);
			rs = pstm.executeQuery();
			Ubigeo bean = null;
			while(rs.next()){
				bean = new Ubigeo();
				bean.setIdDistrito(rs.getString(1));
				bean.setDistrito(rs.getString(2));
				lista.add(bean);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)rs.close();
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {}
		}
		return lista;
	}


	public String traeUbigeo(Ubigeo ubigeoBean)  {
		Connection conn = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		String salida = null;
		try {
			String sql = 
			"select id from ubigeo where "
			+ " departamento =? and provincia=? and distrito=?";
			conn = MySqlDBConexion.getConexion();
			pstm = conn.prepareStatement(sql);
			pstm.setString(1, ubigeoBean.getDepartamento());
			pstm.setString(2, ubigeoBean.getProvincia());
			pstm.setString(3, ubigeoBean.getDistrito());
			log.info(pstm);
			rs = pstm.executeQuery();

			if(rs.next()){
				salida = rs.getString(1);
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)rs.close();
				if (pstm != null)pstm.close();
				if (conn != null)conn.close();
			} catch (SQLException e) {}
		}
		return salida;
	}
	
}
