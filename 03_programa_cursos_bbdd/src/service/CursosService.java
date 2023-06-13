package service;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.Curso;

public class CursosService {
	
	String url="jdbc:mysql://localhost:3306/cursos";
	String usuario = "root";
	String password="*****";
	
	public boolean guardarCurso(int codigoCurso, String nombre, double precio, LocalDate fechaInicio, LocalDate fechaFin) {
		
		boolean res=false;
		
		try(Connection con=DriverManager.getConnection(url, usuario, password)){
			
			String sql = "insert into curso values (?, ?, ?, ?, ?)"; // podemos ahorrar el poner
			// los campos que vamos a rellenar
			PreparedStatement ps = con.prepareStatement(sql);
			
			ps.setInt(1, codigoCurso);
			ps.setString(2, nombre);
			ps.setDouble(3, precio);
			ps.setDate(4, Date.valueOf(fechaInicio));
			ps.setDate(5, Date.valueOf(fechaFin));
			
			res=true;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			res=false;
		}
		return res;
	}
	
	
	public Curso buscarCurso(int codigo) {
		
		Curso curso = null;
        
        try(Connection con = DriverManager.getConnection(url, usuario, password)){
        	
        	String sql="SELECT FROM curso WHERE codigoCurso = ?";
        	PreparedStatement ps = con.prepareStatement(sql);
        	ps.setInt(1, codigo);
        	ResultSet rs = ps.executeQuery();
        	if(rs.next()) {
        		return curso = new Curso(rs.getInt("codigoCurso"),
        				rs.getString("nombre"),
        				rs.getDouble("precio"),
        				rs.getDate("fechaInicio").toLocalDate(),
        				rs.getDate("fechaFin").toLocalDate());
        	}
        } catch (SQLException ex) {
        	ex.printStackTrace();
        }
        
        return curso;
    }
	
	
	public void agregarCurso(List<Curso> cursos) {
		
		try(Connection con=DriverManager.getConnection(url, usuario, password)){
			con.setAutoCommit(false); // desactivamos autocommit para que todos los execute esten en la misma transacción
			String sql = "insert into curso values (?, ?, ?, ?, ?)";
			PreparedStatement ps = con.prepareStatement(sql);
			for(Curso c:cursos) {
				ps.setInt(1, c.getCodigoCurso());
				ps.setString(2, c.getNombre());
				ps.setDouble(3, c.getPrecio());
				ps.setDate(4, Date.valueOf(c.getFechaInicio()));
				ps.setDate(5, Date.valueOf(c.getFechaFin()));
				ps.execute();
			}
			con.commit(); // se confirma si todo ha ido bien
		} catch (SQLException ex) {
			ex.printStackTrace();
			
		}
		
	}
	
	
	public void eliminarCurso(int codigo) {
		
		try(Connection con=DriverManager.getConnection(url, usuario, password)){
			
			String sql="DELETE FROM curso WHERE codigoCurso = ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1,  codigo);
			ps.execute();
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
	
	public List<Curso> cursosPrecioMax(double max){
		List<Curso> cursos = new ArrayList<>();
		
		try(Connection con=DriverManager.getConnection(url, usuario, password)){
			String sql="select from curso where precio >= ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDouble(1, max);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				cursos.add(new Curso(rs.getInt("codigoCurso"),
						rs.getString("nombre"),
        				rs.getDouble("precio"),
        				rs.getDate("fechaInicio").toLocalDate(),
        				rs.getDate("fechaFin").toLocalDate()));
			}
			return cursos;
			
		} catch (SQLException ex) {
			ex.printStackTrace();
			return cursos;
		}
		 
	}
	
	public List<Curso> cursosDuracionMax(int max){
		List<Curso> cursos = new ArrayList<>();
		
		try(Connection con=DriverManager.getConnection(url, usuario, password)){
			String sql="select from curso where duracion <= ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, max);
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				cursos.add(new Curso(rs.getInt("codigoCurso"),
						rs.getString("nombre"),
        				rs.getDouble("precio"),
        				rs.getDate("fechaInicio").toLocalDate(),
        				rs.getDate("fechaFin").toLocalDate()));
			}
			return cursos;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return cursos;
		}

	}
	
	public List<Curso> cursosFinalizados(){
		
		List<Curso> cursos = new ArrayList<>();
		
		try(Connection con=DriverManager.getConnection(url, usuario, password)){
			
			String sql = "select from curso where fechaFin < ?";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1,  Date.valueOf(LocalDate.now())); // le pasamos la fecha de hoy
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				cursos.add(new Curso(rs.getInt("codigoCurso"),
						rs.getString("nombre"),
        				rs.getDouble("precio"),
        				rs.getDate("fechaInicio").toLocalDate(),
        				rs.getDate("fechaFin").toLocalDate()));
			}
			return cursos;
			
			
		} catch(SQLException ex) {
			ex.printStackTrace();
			return cursos;
		}
	}
	
	public Curso cursoLargo() {
		
		Curso curso=null;
		
		try(Connection con=DriverManager.getConnection(url, usuario, password)){
			String sql = "SELECT * FROM curso ORDER BY DATEDIFF(fechaInicio, fechaFin) LIMIT 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
        		return curso = new Curso(rs.getInt("codigoCurso"),
        				rs.getString("nombre"),
        				rs.getDouble("precio"),
        				rs.getDate("fechaInicio").toLocalDate(),
        				rs.getDate("fechaFin").toLocalDate());
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return curso;
		
		}
	
		// Otras opciones
	
		/*
		 * Curso curso=null;
			int max= 0
		try(Connection con=DriverManager.getConnection(url, usuario, password)){
			String sql = "SELECT * FROM curso";
			Statement ps = con createStatement();
			ResultSet rs = ps.executeQuery();
			LocalDate fechaInicio;
			LocalDate fechaFin;
			Period periodo;
			long meses;
			while(rs.next()) {
        		fechaInicio = rs.getDate("fechaInicio").toLocalDate();
        		fechaFin = rs.getDate("fechaFin").toLocalDate();
        		period = Period.between(fechaInicio, fechaFin);
        		meses = period.toTotalMonths();
        		if(meses>max){
        			max = meses;
        			return curso = new Curso(rs.getInt("codigoCurso"),
        				rs.getString("nombre"),
        				rs.getDouble("precio"),
        				rs.getDate("fechaInicio").toLocalDate(),
        				rs.getDate("fechaFin").toLocalDate());
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return curso;
		
		}
		 * 
		 * */
	}

