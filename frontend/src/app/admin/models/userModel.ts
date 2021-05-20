export class UserModel {
    constructor(
        public id?: number,
        public name?: string,
        public email?: string,
        public cpf?: string,
        public token?: string,
        public birthDate?: Date,
    ) {}
}
