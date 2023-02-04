import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
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
  status = false;
  customerForm: FormGroup = new FormGroup(
    {
      idCustomer: new FormControl(''),
      nameCustomer: new FormControl('', [ Validators.required, Validators.pattern(
        '[a-zA-Z _ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
        'ễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+')]),
      emailCustomer: new FormControl('', [Validators.required,
        Validators.pattern('^[A-Za-z0-9_.]{4,32}@([a-zA-Z0-9]{2,12})(.[a-zA-Z]{2,12})+$')]),
      addressCustomer: new FormControl('', Validators.required),
      idCardCustomer: new FormControl('', [Validators.required,
        Validators.pattern('^(\\d{9}|\\d{12})$')]),
      codeCustomer: new FormControl(''),
      genderCustomer: new FormControl('', Validators.required),
      dateOfBirth: new FormControl('', this.checkDateOfBirth),
      flagDelete: new FormControl(''),
      approvalCustomer: new FormControl(''),
      phoneCustomer1: new FormControl('', [Validators.required,
        Validators.pattern('^([+84][0-9]{10})$')]),
      phoneCustomer2: new FormControl('', [Validators.required,
        Validators.pattern('^([+84][0-9]{10})$')]),
      usernameAccount: new FormControl('', [Validators.required,
        Validators.pattern('[a-zA-Z0-9' +
          ' _ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
          'ễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+'),
        Validators.minLength(3)]),
      passGroup: new FormGroup(
        {
          encryptPassword: new FormControl('', [Validators.required,
            Validators.minLength(6)]),
          passwordConfirm: new FormControl('', [Validators.required,
            Validators.minLength(6)])
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
      this.router.navigateByUrl('/customer/create');
    }, error => {
      this.action = false;
    }, () => {
    });
  }

  checkPasswords(group: AbstractControl): any {
    const passwordCheck = group.value;
    return (passwordCheck.encryptPassword === passwordCheck.passwordConfirm ? null : {notSame: true});
  }

  checkDateOfBirth(abstractControl: AbstractControl): any {
    const formYear = Number(new Date(abstractControl.value).getFullYear());
    const formMonth = Number(new Date(abstractControl.value).getMonth() + 1);
    const formDay = Number(new Date(abstractControl.value).getDate());

    return (new Date().getFullYear() - formYear > 15) ? null : {invalidDateOfBirth: true};
  }

  // tslint:disable-next-line:typedef
  onchangeStautus() {

    this.status = !this.status;
    console.log(this.status, this.customerForm.valid);
  }
}
