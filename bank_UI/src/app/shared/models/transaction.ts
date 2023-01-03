import { movementType } from "./movementType";
import { product } from "./product";
import { transactionType } from "./transactionType";


export interface transaction {
    transactionId:number;
    transactionType:transactionType;
    movementType:movementType;
    targetProduct:product;
    movementDate:Date;
    description:string;
    amount:number;
    balance:number;
    availableBalance:number;
}