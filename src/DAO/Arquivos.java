package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import modelos.Pasta;

public class Arquivos extends BasicDAO {
	
	public Pasta buscarPorPasta(int idPasta) throws Exception{
		try{
			Pasta p = new Pasta();
			//p.setPastas(;
			return p;
		}
		catch(Exception e){
			throw e;
		}
	}
}
