import { v4 as uuid_v4 } from 'uuid';

const Mensagem_defaultAttrs = {
	titulo: '',
	conteudo: '',
	upvotes: 0,
	hash: uuid_v4(),
	hash_anterior: '',
	voto: false,
	filaEventosNaoAtualizados: []
}

export default class Mensagem {
	constructor(obj) {
		Object.assign(this, { id: 'msg_' + uuid_v4() }, Mensagem_defaultAttrs, obj);
	}
}

const Tarefa_status = {
	PENDENTE: 'pendente',
	CANCELADA: 'cancelada',
	ENVIADA: 'enviada',
	RECUSADA: 'recusada',
	FINALIZADA: 'finalizada',
	EXCLUIDA: 'excluida'
}

export { Tarefa_status };

const EventoRequisicao_defaultAttrs = {
	tipo: ''
}

export class EventoRequisicao {
	constructor(obj) {
		Object.assign(this, { id: 'evt_' + uuid_v4() }, EventoRequisicao_defaultAttrs, obj);
	}
}