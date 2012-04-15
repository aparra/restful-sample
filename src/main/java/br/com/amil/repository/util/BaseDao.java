package br.com.amil.repository.util;

import java.util.ArrayList;
import java.util.List;

import br.com.amil.model.Beneficiario;
import br.com.caelum.vraptor.ioc.Component;

@Component
public class BaseDao {

	public static List<Beneficiario> beneficiarios = new ArrayList<Beneficiario>();
	
}
