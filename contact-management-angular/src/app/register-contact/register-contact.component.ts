import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, FormArray } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Identification } from 'src/model/Identification';
import { SpringResponse } from 'src/model/SpringResponse';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Communication } from 'src/model/Communication';
import { Address } from 'src/model/Address';
import { Inject } from '@angular/core';
import { environment } from './../../environments/environment';

@Component({
  selector: 'app-register-contact',
  templateUrl: './register-contact.component.html',
  styleUrls: ['./register-contact.component.css']
})
export class RegisterContactComponent implements OnInit {

  form: FormGroup;
  hide = false;
  selected: string = 'm';
  selectedType: string = 'home';
  address!: FormArray;
  communication!: FormArray;
  isUpdate = false;

  readonly urlCreate = environment.apiUrl + '/identification/create';
  readonly urlUpdate = environment.apiUrl + '/identification/update';

  constructor(
    private fb: FormBuilder,
    private http: HttpClient,
    public dialogRef: MatDialogRef<RegisterContactComponent>,
    private snackBar: MatSnackBar,
    @Inject(MAT_DIALOG_DATA) public _data: any
  ) {
    this.form = this.fb.group({
      id: '',
      status: '',
      createdBy: '',
      modifiedBy: '',
      createdDatetime: '',
      modifiedDatetime: '',
      verNo: '',
      firstName: [null, Validators.required],
      lastName: [null, Validators.required],
      dob: [null, Validators.required],
      gender: null,
      title: [null, Validators.required],
      address: this.fb.array([
        // this.fb.group({
        //   id: '',
        //   status: '',
        //   createdBy: '',
        //   modifiedBy: '',
        //   createdDatetime: '',
        //   modifiedDatetime: '',
        //   verNo: '',
        //   identification: '',
        //   type: [null, Validators.required],
        //   number: [null, Validators.required],
        //   street: [null, Validators.required],
        //   unit: [null, Validators.required],
        //   city: [null, Validators.required],
        //   state: [null, Validators.required],
        //   zipCode: [null, Validators.required],
        // })
      ]),
      communication: this.fb.array([
        // this.fb.group({
        //   id: '',
        //   status: '',
        //   createdBy: '',
        //   modifiedBy: '',
        //   createdDatetime: '',
        //   modifiedDatetime: '',
        //   verNo: '',
        //   identification: '',
        //   type: ['email', Validators.required],
        //   value: [null, Validators.required],
        //   preferred: [false, Validators.required]
        // })
      ])
    });
  }

  ngOnInit(): void {
    this.isUpdate = this._data && this._data.isUpdate ? true : false
    console.log(this.isUpdate)
    if (this.isUpdate) {
      // this.form.setValue(this._data.dataById.result)
      this.form = this.fb.group({
        id: this._data.dataById.result.id,
        firstName: [this._data.dataById.result.firstName, Validators.required],
        lastName: [this._data.dataById.result.lastName, Validators.required],
        dob: [this._data.dataById.result.dob, Validators.required],
        gender: this._data.dataById.result.gender,
        title: [this._data.dataById.result.title, Validators.required],
        address: this.fb.array([]),
        communication: this.fb.array([])
      });
      let formArrayAddress = this.form.get('address') as FormArray
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
      this.form.setControl('address', formArrayAddress);

      let formArrayCommunication = this.form.get('communication') as FormArray
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
      this.form.setControl('communication', formArrayCommunication);
    }
  }

  getAddressControl() {
    return this.form.get('address') as FormArray;
  }

  getCommunicationControl() {
    return this.form.get('communication') as FormArray;
  }

  addAddress() {
    this.address = this.form.get("address") as FormArray
    this.address.push(
      this.fb.group({
        type: 'home',
        number: '',
        street: '',
        unit: '',
        city: '',
        state: '',
        zipCode: '',
      })
    )
  }

  deleteAddress(index: number) {
    this.address = this.form.get("address") as FormArray
    this.address.removeAt(index)
  }

  addCommunication() {
    this.communication = this.form.get("communication") as FormArray
    this.communication.push(
      this.fb.group({
        type: 'email',
        value: '',
        preferred: false
      })
    )
  }

  deleteCommunication(index: number) {
    this.communication = this.form.get("communication") as FormArray
    this.communication.removeAt(index)
  }

  saveContact() {
    const value = this.form.value;
    let headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('userID', 'djrumix');
    let adds: Address[] = value.address;
    let coms: Communication[] = value.communication
    let newAdd = adds.map(function (val, index) {
      val.createdBy = "Alona"
      let temp = val;
      delete temp.id;
      delete temp.identification;
      return temp;
    })

    let newCom = coms.map(function (val, index) {
      val.createdBy = "Alona"
      let temp = val;
      delete temp.id;
      delete temp.identification;
      return temp;
    })

    let createRequest: Identification = {
      id: 0,
      firstName: value.firstName,
      lastName: value.lastName,
      dob: value.dob,
      gender: this.selected,
      title: value.title,
      address: newAdd,
      communication: newCom,
      createdBy: "Alona",
      modifiedBy: "",
      status: ""
    }
    console.log(createRequest)
    const res = this.http.post<SpringResponse<Identification>>(
      this.urlCreate, createRequest, { headers }
    ).subscribe((data: SpringResponse<Identification>) => {
      this.snackBar.open("Api Response: " + JSON.stringify(data), undefined, {
        duration: 10000,
      });
    }, (error: any[]) => {
      this.snackBar.open("Api Error: " + JSON.stringify(error), undefined, {
        duration: 10000,
      });
      console.error(error);
    })
    this.dialogRef.close()
  }

  updateContact() {
    const value = this.form.value;
    let headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('userID', 'djrumix');
    let adds: Address[] = value.address;
    let coms: Communication[] = value.communication
    let newAdd = adds.map(function (val, index) {
      val.createdBy = "AlonaX"
      val.modifiedBy = "Alona"
      val.status = "ACT"
      return val;
    })
    let newCom = coms.map(function (val, index) {
      val.createdBy = "AlonaX"
      val.modifiedBy = "Alona";
      val.status = "ACT";
      return val;
    })

    let updateRequest: Identification = {
      id: value.id,
      firstName: value.firstName,
      lastName: value.lastName,
      dob: value.dob,
      gender: this.selected,
      title: value.title,
      address: newAdd,
      communication: newCom,
      createdBy: "AlonaX",
      modifiedBy: "AlonaX",
      status: "ACT"
    }
    console.log(updateRequest)
    const res = this.http.put<SpringResponse<Identification>>(
      this.urlUpdate, updateRequest, { headers }
    ).subscribe((data: SpringResponse<Identification>) => {
      this.snackBar.open("Api Response: " + JSON.stringify(data), undefined, {
        duration: 10000,
      });
    }, (error: any[]) => {
      this.snackBar.open("Api Error: " + JSON.stringify(error), undefined, {
        duration: 10000,
      });
      console.error(error);
    })
    this.dialogRef.close()
  }

  close(){
    this.dialogRef.close();
  }

}
