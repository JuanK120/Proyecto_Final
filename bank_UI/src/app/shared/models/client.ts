import { identificationType } from "./identificationType";
import { users } from "./users";


export interface client {
    clientId:number;
    idType:identificationType;
    idNumber:number;
    name:string;
    lastName:string;
    email:string;
    birthDate:Date;
    creationDate:Date;
    creationUser:users;
    modificationDate:Date;
    modificationUser:users;
    active:boolean;
}