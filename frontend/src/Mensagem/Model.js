import { v4 as uuid_v4 } from 'uuid';

const Mensagem_defaultAttrs = {
	titulo: '',
	conteudo: '',
	likes: 0
}

export default class Mensagem {
	constructor(obj) {
		Object.assign(this, { id: 'msg_' + uuid_v4() }, Mensagem_defaultAttrs, obj);
	}
}

const EventoRequisicao_defaultAttrs = {
	tipo: '',
	mensagem: new Mensagem()
}

export class EventoRequisicao {
	constructor(obj) {
		Object.assign(this, { id: 'evt_' + uuid_v4() }, EventoRequisicao_defaultAttrs, obj);
	}
}