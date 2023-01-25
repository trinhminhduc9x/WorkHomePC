// import {Component, OnInit} from '@angular/core';
// import {AbstractControl, FormBuilder, FormGroup, Validators} from '@angular/forms';
// import {TicketService} from '../../service/ticket.service';
// import {Category} from '../../model/category';
// import {Router} from '@angular/router';
//
// @Component({
//   selector: 'app-create-ticket',
//   templateUrl: './create-ticket.component.html',
//   styleUrls: ['./create-ticket.component.css']
// })
// export class CreateTicketComponent implements OnInit {
//
//   rfTicket: FormGroup;
//
//   garages: Category[] | undefined;
//
//   constructor(private formBuilder: FormBuilder,
//               private ticketService: TicketService,
//               private router: Router) {
//   }
//
//   ngOnInit(): void {
//     this.createForm();
//     this.getTicketTypes();
//   }
//
//   getTicketTypes() {
//     this.ticketService.findAllTicketTypes().subscribe(
//       data => {
//         this.garages = data;
//       }, error => {
//         this.ticketService.showErrorNotification('Có lỗi khi tải Loại khách hàng!');
//       }
//     );
//   }
//
//   createForm() {
//     this.rfTicket = this.formBuilder.group({
//       price: ['', [
//         Validators.required,
//         Validators.pattern('^\\d+$')
//       ]],
//       quantity: ['', [
//         Validators.required,
//         Validators.pattern('^\\d+$')
//       ]],
//       fromPlace: ['', [
//         Validators.required
//       ]],
//       toPlace: ['', [
//         Validators.required
//       ]],
//       startDate: ['', [
//         Validators.required,
//         Validators.pattern('^\\d{4}[-]\\d{2}[-]\\d{2}$'),
//         this.checkStartDateOnFuture
//       ]],
//       startHour: ['', [
//         Validators.required,
//         Validators.pattern('^(([0,1][0-9])|([2][0-4]))\\:(([0-5][0-9])|(60))$')
//       ]],
//       garage: ['', [
//         Validators.required
//       ]],
//     },);
//   }
//
//   submit() {
//     const ticket = this.rfTicket.value;
//     this.ticketService.save(ticket).subscribe(
//       data => {
//         this.ticketService.showSuccessNotification('Thêm mới thành công!');
//         this.createForm();
//       }, error => {
//         this.ticketService.showErrorNotification('Thêm mới thất bại!');
//       }, () => {
//         this.router.navigateByUrl('/tickets');
//       });
//   }
//
//   resetForm() {
//     this.ngOnInit();
//   }
//
//   checkStartDateOnFuture(abstractControl: AbstractControl): any {
//     const startDate = new Date(abstractControl.value).getTime();
//     const now = new Date().getTime();
//     return (startDate - now > 24 * 60 * 60 * 1000) ? null : {invalidStartDate: true};
//   }
//
// }
//
