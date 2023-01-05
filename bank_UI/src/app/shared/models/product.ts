import { accountState } from "./accountState";
import { client } from "./client";
import { productType } from "./productType";
import { users } from "./users";


export interface product {
    productId:number;
    owner:client;
    productType:productType;
    productNumber:number;
    state:accountState;
    balance:number;
    availableBalance:number;
    gmfExempt:boolean;
    creationDate:Date
    creationUser:users;
    modificationDate:Date;
    modificationUser:users;
    
}