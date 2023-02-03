import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn} from '@angular/forms';
import {Router} from '@angular/router';
import {Account} from '../../entity/account/account';
import {Customer} from '../../entity/customer/customer';
import {CustomerService} from '../../service/customer.service';

export const checkBirthDay: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
  // @ts-ignore
  const birthday = new Date(control.get('birthDay').value).getTime();
  console.log(birthday);
  const dateNow = new Date().getTime();
  console.log(dateNow);
  if (dateNow - birthday < 18 * 365 * 24 * 60 * 60 * 1000) {
    return {checkBirthDay: true};
  } else {
    return null;
  }
};


@Component({
  selector: 'app-customer-create',
  templateUrl: './customer-create.component.html',
  styleUrls: ['./customer-create.component.css']
})
export class CustomerCreateComponent implements OnInit {

  submitted = false;
  action = true;

  customerForm: FormGroup = new FormGroup(
    {
      idCustomer: new FormControl(''),
      nameCustomer: new FormControl(''),
      emailCustomer: new FormControl(''),
      addressCustomer: new FormControl(''),
      idCardCustomer: new FormControl(''),
      codeCustomer: new FormControl(''),
      genderCustomer: new FormControl(''),
      dateOfBirth: new FormControl(''),
      flagDelete: new FormControl(''),
      approvalCustomer: new FormControl(''),
      phoneCustomer1: new FormControl(''),
      phoneCustomer2: new FormControl(''),
      usernameAccount: new FormControl(''),
      passGroup: new FormGroup(
        {
          encryptPassword: new FormControl(''),
          passwordConfirm: new FormControl('')
        }),
    });

  account = new Account();
  customer: Customer | undefined;



  constructor(private customerService: CustomerService,
              private router: Router) {
  }

  ngOnInit(): void {
    // tslint:disable-next-line:only-arrow-functions typedef
    window.onbeforeunload = function(e: Event | undefined) {
      e = e || window.event;
      // For IE and Firefox prior to version 4
      if (e) {
        e.returnValue = Boolean('Sure?');
      }
      // For Safari
      return 'Sure?';
    };
  }

  submit(): void {
    this.submitted = true;
    this.customer = this.customerForm.value;
    this.account.usernameAccount = this.customerForm.value.usernameAccount;
    // @ts-ignore
    this.account.encryptPassword = this.customerForm.get('passGroup').get('encryptPassword').value;
    this.account.email = this.customerForm.value.emailCustomer;
    this.account.name = this.customerForm.value.nameCustomer;
    // @ts-ignore
    this.customer?.account = this.account;
    // @ts-ignore
    console.log(this.customerForm.get('passGroup').get('encryptPassword').value);
    this.customerService.saveCustomer(this.customer).subscribe(value => {
      console.log(value);
      this.customerForm.reset();
      this.router.navigateByUrl('/customer/create');
    }, error => {
      this.action = false;
    }, () => {
    });
  }
}
