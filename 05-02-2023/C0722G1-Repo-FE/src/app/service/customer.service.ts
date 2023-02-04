import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Account} from '../entity/account/account';
import {Customer} from '../entity/customer/customer';

@Injectable({
  providedIn: 'root'
})
export class CustomerService {

  private urlCustomer = 'http://localhost:8080/api/customer/signup';

  constructor(private httpClient: HttpClient) { }

  // tslint:disable-next-line:typedef
  save(account: Account){
    return this.httpClient.post('http://localhost:8080/api/customer/signup', account);
  }

  // tslint:disable-next-line:typedef
  saveCustomer(customer: Customer | undefined) {
    console.log(customer);
    return this.httpClient.post<Customer>(this.urlCustomer, customer);
  }

}
