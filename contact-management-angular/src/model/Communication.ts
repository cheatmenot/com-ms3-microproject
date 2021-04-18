export class Communication {
    id?: number;
    type: string;
    value: string;
    preferred: string;
    identification?: number;
    createdBy: string;
    modifiedBy: string;
    status: string;
  
    constructor(
        id: number,
        type: string,
        value: string,
        preferred: string,
        identification: number,
        createdBy: string,
        modifiedBy: string,
        status: string,
      ) {
          this.id = id;
          this.type = type;
          this.value = value;
          this.preferred = preferred;
          this.identification = identification;
          this.createdBy = createdBy;
          this.modifiedBy = modifiedBy;
          this.status = status;
      }
  }