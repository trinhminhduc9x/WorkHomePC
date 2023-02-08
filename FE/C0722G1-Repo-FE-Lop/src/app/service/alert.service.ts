import { Injectable } from '@angular/core';
import {ToastrService} from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class AlertService {
  constructor(private toast: ToastrService) { }
  showMessage(message:any){
    this.toast.success(message, "Thông báo:",{
      timeOut:2000,
    });
  }

  showMessageErrors(message:any){
    this.toast.error(message, "Thông báo:",{
      timeOut:2000
    });
  }
}
