package social.teste.backend.controller.configuracao;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.util.Assert;

public class EntityWrapper<T> extends RepresentationModel<EntityWrapper<T>> {

	private T conteudo;

	/**
	 * Creates an empty {@link EntityModel}.
	 */
	protected EntityWrapper() {
		this.conteudo = null;
	}

	/**
	 * Creates a new {@link EntityWrapper} with the given conteudo and {@link Link}s
	 * (optional).
	 *
	 * @param conteudo must not be {@literal null}.
	 * @param links    the links to add to the {@link EntityWrapper}.
	 * @deprecated since 1.1, use {@link #of(Object, Link...)} instead.
	 */
	@Deprecated
	public EntityWrapper(T conteudo, Link... links) {
		this(conteudo, Arrays.asList(links));
	}

	/**
	 * Creates a new {@link EntityWrapper} with the given conteudo and
	 * {@link Link}s.
	 *
	 * @param conteudo must not be {@literal null}.
	 * @param links    the links to add to the {@link EntityWrapper}.
	 * @deprecated since 1.1, use {@link #of(Object, Iterable)} instead.
	 */
	@Deprecated
	public EntityWrapper(T conteudo, Iterable<Link> links) {

		Assert.notNull(conteudo, "conteudo must not be null!");
		Assert.isTrue(!(conteudo instanceof Collection),
				"conteudo must not be a collection! Use CollectionModel instead!");

		this.conteudo = conteudo;
		this.add(links);
	}

	/**
	 * Creates a new {@link EntityWrapper} with the given conteudo.
	 *
	 * @param conteudo must not be {@literal null}.
	 * @return
	 * @since 1.1
	 */
	public static <T> EntityWrapper<T> of(T conteudo) {
		return of(conteudo, Collections.emptyList());
	}

	/**
	 * Creates a new {@link EntityWrapper} with the given conteudo and {@link Link}s
	 * (optional).
	 *
	 * @param conteudo must not be {@literal null}.
	 * @param links    the links to add to the {@link EntityWrapper}.
	 * @return
	 * @since 1.1
	 */
	public static <T> EntityWrapper<T> of(T conteudo, Link... links) {
		return of(conteudo, Arrays.asList(links));
	}

	/**
	 * Creates a new {@link EntityWrapper} with the given conteudo and
	 * {@link Link}s.
	 *
	 * @param conteudo must not be {@literal null}.
	 * @param links    the links to add to the {@link EntityWrapper}.
	 * @return
	 * @since 1.1
	 */
	public static <T> EntityWrapper<T> of(T conteudo, Iterable<Link> links) {
		return new EntityWrapper<>(conteudo, links);
	}

	public T getConteudo() {
		return conteudo;
	}

	public void setConteudo(T conteudo) {
		this.conteudo = conteudo;
	}
}