import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Product} from '../model/product';
import {environment} from '../../environments/environment';
import {Category} from '../model/category';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class TicketService {

  constructor(private httpClient: HttpClient,
              private toastrService: ToastrService) {
  }

  findAll(): Observable<Product[]> {
    return this.httpClient.get<Product[]>(environment.api_url_products + '?_sort=id&_order=desc');
  }

  searchTwoField(from: string, to: string): Observable<Product[]> {
    return this.httpClient.get<Product[]>(
      `${environment.api_url_products}?fromPlace_like=${from}&toPlace_like=${to}`
    );
  }

  save(ticket: Product): Observable<Product> {
    return this.httpClient.post<Product>(environment.api_url_products, ticket);
  }

  update(ticket: Product): Observable<Product> {
    return this.httpClient.patch<Product>(environment.api_url_products + '/' + ticket.id, ticket);
  }

  findById(id: number): Observable<Product> {
    return this.httpClient.get<Product>(environment.api_url_products + '/' + id);
  }

  remove(ticket: Product): Observable<Product> {
    return this.httpClient.delete<Product>(environment.api_url_products + '/' + ticket.id);
  }

  findAllTicketTypes(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(environment.api_url_categories);
  }

  showSuccessNotification(message: string) {
    this.toastrService.success(message, 'Thông Báo', {
      timeOut: 2000,
      progressBar: true,
      positionClass: 'toast-top-right',
      easing: 'ease-in'
    });
  }

  showErrorNotification(message: string) {
    this.toastrService.error(message, 'Lỗi', {
      timeOut: 2000,
      progressBar: true,
      positionClass: 'toast-top-right',
      easing: 'ease-in'
    });
  }

  showWarningNotification(message: string) {
    this.toastrService.warning(message, 'Cảnh báo', {
      timeOut: 2000,
      progressBar: true,
      positionClass: 'toast-top-right',
      easing: 'ease-in'
    });
  }
}


