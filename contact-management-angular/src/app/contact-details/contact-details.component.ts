import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Address } from 'src/model/Address';
import { Communication } from 'src/model/Communication';

@Component({
  selector: 'app-contact-details',
  templateUrl: './contact-details.component.html',
  styleUrls: ['./contact-details.component.css']
})
export class ContactDetailsComponent implements OnInit {

  form: FormGroup;
  selected: string = 'm';

  constructor(
    private fb: FormBuilder,
    public dialogRef: MatDialogRef<ContactDetailsComponent>,
    @Inject(MAT_DIALOG_DATA) public _data: any
  ) { 
    console.log("_data: " + JSON.stringify(_data.dataById.result))
    var temp = this.fb.group({
      id: this._data.dataById.result.id,
      firstName: [this._data.dataById.result.firstName, Validators.required],
      lastName: [this._data.dataById.result.lastName, Validators.required],
      dob: [this._data.dataById.result.dob, Validators.required],
      gender: this._data.dataById.result.gender,
      title: [this._data.dataById.result.title, Validators.required],
      address: this.fb.array([]),
      communication: this.fb.array([])
    });
    let formArrayAddress = temp.get('address') as FormArray
    if (formArrayAddress) {
      this._data.dataById.result.address.map((item: Address) => {
        formArrayAddress.push(
          this.fb.group({
            id: item.id,
            type: [item.type, Validators.required],
            number: [item.number, Validators.required],
            street: [item.street, Validators.required],
            unit: [item.unit, Validators.required],
            city: [item.city, Validators.required],
            state: [item.state, Validators.required],
            zipCode: [item.zipCode, Validators.required],
          })
        )
      })
    }
    temp.setControl('address', formArrayAddress);

    let formArrayCommunication = temp.get('communication') as FormArray
    if (formArrayCommunication) {
      this._data.dataById.result.communication.map((item: Communication) => {
        formArrayCommunication.push(
          this.fb.group({
            id: item.id,
            type: [item.type, Validators.required],
            value: [item.value, Validators.required],
            preferred: [item.preferred, Validators.required]
          })
        )
      })
    }
    temp.setControl('communication', formArrayCommunication);
    this.form = temp;
  }

  ngOnInit(): void {
  }

  getAddressControl() {
    return this.form.get('address') as FormArray;
  }

  getCommunicationControl() {
    return this.form.get('communication') as FormArray;
  }

  close(){
    this.dialogRef.close();
  }

}
