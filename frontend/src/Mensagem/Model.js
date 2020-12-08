import { v4 as uuid_v4 } from 'uuid';

const Mensagem_defaultAttrs = {
	titulo: '',
	conteudo: ''
}

export default class Mensagem {
	constructor(obj) {
		Object.assign(this, { id: 'msg_' + uuid_v4() }, Mensagem_defaultAttrs, obj);
	}
}