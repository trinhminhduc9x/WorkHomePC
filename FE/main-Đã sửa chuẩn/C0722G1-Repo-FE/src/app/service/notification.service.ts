import {Injectable} from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {PageNotificationDto} from '../dto/notification/page-notification-dto';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private URL_API_NOTIFICATION = 'http://localhost:8080/api/notifications';
  constructor(private httpClient: HttpClient, private toast: ToastrService) {
  }
  /**
   * Created: DatLA
   * Function: get all and search notifications
   * @Param searchNotification,pageNumber
   * Date: 31/01/2023
   */
  getPageNotifications(searchNotification: any, pageNumber: any): Observable<PageNotificationDto> {
    return this.httpClient.post<PageNotificationDto>(this.URL_API_NOTIFICATION +
      '/search?page=' + pageNumber, searchNotification);
  }
}
