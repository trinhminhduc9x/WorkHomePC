import {Component, OnInit} from '@angular/core';
import {AbstractControl, FormBuilder, FormControl, FormGroup, ValidationErrors, ValidatorFn, Validators} from '@angular/forms';
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
  // private customerForm: FormGroup | undefined;
  constructor(private customerService: CustomerService,
              private router: Router,
              private formBuilder: FormBuilder) {
  }

  submitted = false;
  action = true;
  status = false;
  account = new Account();
  customer: Customer | undefined;
  result = false;
  private customerList: Customer[] | undefined;
  customerForm: FormGroup = new FormGroup(
    {
      idCustomer: new FormControl(''),
      nameCustomer: new FormControl('', [ Validators.required, Validators.pattern(
        '[a-zA-Z _ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
        'ễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+')]),
      emailCustomer: new FormControl('', [Validators.required,
        Validators.pattern('^[A-Za-z0-9_.]{4,32}@([a-zA-Z0-9]{2,12})(.[a-zA-Z]{2,12})+$')], this.isExist),
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
        Validators.minLength(3)], this.areEqual),
      passGroup: new FormGroup(
        {
          encryptPassword: new FormControl('', [Validators.required,
            Validators.minLength(6)]),
          passwordConfirm: new FormControl('', [Validators.required,
            Validators.minLength(6)])
        }, this.checkPasswords
      )
    });

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
  getAllCustomer(): void {
    this.customerService.findAllCustomer().subscribe(list => {
      this.customerList = list;
    }, error => {
      console.log(error);
    });
  }

  //
  // // tslint:disable-next-line:typedef
  // getCreateForm() {
  //   this.customerService.findAllCustomer().subscribe(data => {
  //     this.customerList = data;
  //     this.customerForm = this.formBuilder.group({
  //       idCustomer: [''],
  //       nameCustomer: ['', [ Validators.required, Validators.pattern(
  //                 '[a-zA-Z _ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
  //                 'ễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+')]],
  //       emailCustomer: ['', [Validators.required,
  //         Validators.pattern('^[A-Za-z0-9_.]{4,32}@([a-zA-Z0-9]{2,12})(.[a-zA-Z]{2,12})+$')]],
  //       addressCustomer: ['', [Validators.required]],
  //       idCardCustomer: ['', [Validators.required,
  //         Validators.pattern('^(\\d{9}|\\d{12})$')]],
  //       codeCustomer: [''],
  //       genderCustomer: ['', [Validators.required]],
  //       dateOfBirth: ['', this.checkDateOfBirth],
  //       flagDelete: [''],
  //       approvalCustomer: [''],
  //       phoneCustomer1: ['', [Validators.required,
  //         Validators.pattern('^([+84][0-9]{10})$')] ],
  //       phoneCustomer2: ['', [Validators.required,
  //         Validators.pattern('^([+84][0-9]{10})$')]],
  //       usernameAccount: ['', [Validators.required,
  //         Validators.pattern('[a-zA-Z0-9' +
  //           ' _ÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪ' +
  //           'ễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ]+'),
  //         Validators.minLength(3)]],
  //       passGroup: new FormGroup(
  //         {
  //           encryptPassword: new FormControl('', [Validators.required,
  //             Validators.minLength(6)]),
  //           passwordConfirm: new FormControl('', [Validators.required,
  //             Validators.minLength(6)])
  //         }, {validators: [ this.areEqual , this.isExist]},
  //       )
  //       }, {validators: [this.areEqual, this.isExist]},
  //     );
  //   });
  // }

  submit(): void {
    this.submitted = true;
    // @ts-ignore
    this.customer = this.customerForm.value;
    // @ts-ignore
    this.account.usernameAccount = this.customerForm.value.usernameAccount;
    // @ts-ignore
    this.account.encryptPassword = this.customerForm.get('passGroup').get('encryptPassword').value;
    // @ts-ignore
    this.account.email = this.customerForm.value.emailCustomer;
    // @ts-ignore
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
    // @ts-ignore
    console.log(this.status, this.customerForm.valid);
  }

  areEqual: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
    // @ts-ignore
    const usernameAccount = control.get('usernameAccount').value;
    console.log(usernameAccount);
    let result = null;
    // @ts-ignore
    this.customerList.forEach(value => {
      // @ts-ignore
      if (usernameAccount === value.account.usernameAccount) {
        result = {areEqual: true};
        console.log(result);
      }
    });
    return result;
  }
  isExist: ValidatorFn = (control: AbstractControl): ValidationErrors | null => {
    // @ts-ignore
    const email = control.get('emailCustomer').value;
    console.log(email);
    let result = null;
    // @ts-ignore
    this.customerList.forEach(value => {
      if (email === value.emailCustomer) {
        result = {isExist: true};
      }
    });
    return result;
    console.log(result);
  }
}
