import { AfterViewInit, Component, OnInit, ViewChild } from '@angular/core';
import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { SpringResponse } from 'src/model/SpringResponse';
import { Identification } from 'src/model/Identification';
import { MatDialog } from '@angular/material/dialog';
import { RegisterContactComponent } from './register-contact/register-contact.component';
import { MatSnackBar } from '@angular/material/snack-bar';
import { ContactDetailsComponent } from './contact-details/contact-details.component';
import { MatSort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { environment } from './../environments/environment';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit, AfterViewInit {
  constructor(
    private http: HttpClient,
    private dialog: MatDialog,
    private snackBar: MatSnackBar,
  ) {
    this.http.get<any>(this.urlGetListing, {headers: this.headers}).subscribe((data: SpringResponse<Identification[]>) => {
      console.log(JSON.stringify(data)) 
      this.identificationListing = data.result;
      this.dataSource = new MatTableDataSource(data.result)
      if (this.sort) {
        this.dataSource.sort = this.sort;
      }
    })
    
  }

  readonly urlGetListing = environment.apiUrl + '/identification/getAll';
  readonly urlDeleteRow = environment.apiUrl + '/identification/delete';
  readonly urlGetId = environment.apiUrl + '/identification/getId';

  @ViewChild(MatSort) sort?: MatSort;

  dataSource: MatTableDataSource<Identification> = new MatTableDataSource<Identification>([]);

  ngAfterViewInit() {
    // this.dataSource.sort = this.sort;
  }

  headers = new HttpHeaders()
      .set('Content-Type', 'application/json')
      .set('userID', 'djrumix');

  identificationListing: Identification[] = [];

  ngOnInit() {
    this.http.get<any>(this.urlGetListing, {headers: this.headers}).subscribe((data: SpringResponse<Identification[]>) => {
      console.log(JSON.stringify(data)) 
      this.identificationListing = data.result;
      this.dataSource = new MatTableDataSource(data.result)
      if (this.sort) {
        this.dataSource.sort = this.sort;
      }
    })
  }

  title = 'contact-management-angular';
  displayedColumns: string[] = ['firstName', 'lastName', 'dob', 'gender', 'title', "action"];
  // dataSource = ELEMENT_DATA;

  addDialog(){
    this.dialog.open(RegisterContactComponent, {
      height: '800px',
      width: '1200px',
    }).afterClosed().subscribe(() => {
      this.ngOnInit();
    });
  }

  deleteRow(id: number){
    let params = new HttpParams()
      .set("id", id.toString())
    this.http.delete<any>(
      this.urlDeleteRow, 
      { headers: this.headers, params: params}
    ).subscribe(data => {
      this.snackBar.open("Api Response: " + JSON.stringify(data), undefined, {
        duration: 10000,
      });
      this.ngOnInit();
    }, (error: any[]) => {
      this.snackBar.open("Api Error: " + error, undefined, {
        duration: 10000,
      });
      console.error(error);
    })
    console.log(params)
  }

  editRow(row: Identification){
    let params = new HttpParams()
      .set("id", row.id.toString())
    this.http.get<SpringResponse<Identification>>(
      this.urlGetId,
      { headers: this.headers, params: params}
    ).subscribe(data => {
      this.snackBar.open("Api Response: " + JSON.stringify(data), undefined, {
        duration: 10000,
      });
      this.dialog.open(RegisterContactComponent, {
        height: '800px',
        width: '1200px',
        data: { dataById: data, isUpdate: true }
      }).afterClosed().subscribe(() => {
        this.ngOnInit();
      });
    }, (error: any[]) => {
      this.snackBar.open("Api Error: " + error, undefined, {
        duration: 10000,
      });
      console.error(error);
    })
    console.log(row)
  }

  viewRow(row: Identification){
    let params = new HttpParams()
      .set("id", row.id.toString())
    this.http.get<SpringResponse<Identification>>(
      this.urlGetId,
      { headers: this.headers, params: params}
    ).subscribe(data => {
      this.dialog.open(ContactDetailsComponent, {
        height: '800px',
        width: '1200px',
        data: { dataById: data }
      })
    }, (error: any[]) => {
      this.snackBar.open("Api Error: " + error, undefined, {
        duration: 10000,
      });
      console.error(error);
    })
  }

}
