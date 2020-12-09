package social.teste.backend.entidade.configuracao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.ObjectIdGenerators.UUIDGenerator;

public class EntidadeUtil {

	public static UUIDGenerator gen = new UUIDGenerator();

	public static String geraHash(Object... objetos) {
		List<String> hashs = new ArrayList<>();
		for (Object object : objetos) {
			hashs.add(gen.generateId(Optional.of(object).orElse(new Object())).toString());
		}
		return gen.generateId(StringUtils.join(hashs, " ")).toString();
	}
}