import React from 'react';

import CardExpansivel from '../../componentes/CardExpansivel';
import { ContextoMensagemSelecionada } from './Mensagens';

export default function ConteudoMensagem(props) {
	return (
		<ContextoMensagemSelecionada.Consumer>
			{ (selecionada) =>
				<CardExpansivel titulo="Mensagem Selecionada" corpo={ !selecionada ? null : (
							<div className={selecionada.id}>
								<h3>{selecionada.titulo}</h3>
								<p>{selecionada.conteudo}</p>
							</div>
						)} />
			}
		</ContextoMensagemSelecionada.Consumer>
	);
}