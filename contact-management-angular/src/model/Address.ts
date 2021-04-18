export class Address {
    id?: number;
    type: string;
    number: string;
    street: string;
    unit: string;
    city: string;
    state: string;
    zipCode: string;
    identification?: number;
    createdBy: string;
    modifiedBy: string;
    status: string;
  
    constructor(
        id: number,
        type: string,
        number: string,
        street: string,
        unit: string,
        city: string,
        state: string,
        zipCode: string,
        identification: number,
        createdBy: string,
        modifiedBy: string,
        status: string,
      ) {
          this.id = id;
          this.type = type;
          this.number = number;
          this.street = street;
          this.unit = unit;
          this.city = city;
          this.state = state;
          this.zipCode = zipCode;
          this.createdBy = createdBy;
          this.modifiedBy = modifiedBy;
          this.status = status;
          this.identification = identification;
      }
  }