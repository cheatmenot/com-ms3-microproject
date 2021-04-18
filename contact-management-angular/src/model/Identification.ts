import { Address } from "./Address";
import { Communication } from "./Communication"

export class Identification {
    id: number;
    firstName: string;
    lastName: string;
    dob: string;
    gender: string;
    title: string;
    address?: Address[];
    communication?: Communication[];
    createdBy: string;
    modifiedBy: string;
    status: string;
  
    constructor(
        id: number,
        firstName: string,
        lastName: string,
        dob: string,
        gender: string,
        title: string,
        address: Address[],
        communication: Communication[],
        createdBy: string,
        modifiedBy: string,
        status: string,
      ) {
          this.id = id;
          this.firstName = firstName;
          this.lastName = lastName;
          this.dob = dob;
          this.gender = gender;
          this.title = title;
          this.address = address;
          this.communication = communication;
          this.createdBy = createdBy;
          this.modifiedBy = modifiedBy;
          this.status = status;
      }
  }