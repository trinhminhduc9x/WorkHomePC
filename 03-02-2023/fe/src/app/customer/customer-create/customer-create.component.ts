import { Component, OnInit } from '@angular/core';
import {AbstractControl, FormControl, FormGroup, Validators} from '@angular/forms';
import {Router} from '@angular/router';
import {AccountService} from '../../service/account.service';
import {Account} from '../../entity/account/account';


@Component({
  selector: 'app-customer-create',
  templateUrl: './customer-create.component.html',
  styleUrls: ['./customer-create.component.css']
})
export class CustomerCreateComponent implements OnInit {
  registerAccount: FormGroup;

  account: Account;

  errorList: any;

  constructor(private accountService: AccountService,
              private router: Router) {
    this.registerAccount = new FormGroup({
      email: new FormControl('', [Validators.required, Validators.pattern(/^[\w-\.]+@(gmail\.)+(com)$/)]),

      phone: new FormControl('', [Validators.required, Validators.pattern(/^(090|093|097)\d{7}$/)]),

      passwordGroup: new FormGroup({
        password: new FormControl('', [Validators.required, Validators.minLength(6)]),

        passwordConfirm: new FormControl()
      }, [this.checkPassword]),
    });
  }

  ngOnInit(): void {
  }

  save() {
    if (this.registerAccount.valid){
      const account: Account = this.registerAccount.value;
      account.password = this.registerAccount.controls.passwordGroup.get('password').value;
      this.accountService.save(account).subscribe(next => {
        this.router.navigateByUrl('');
      }, error => {
        this.errorList = error.error;
      });
    }
  }

  private checkPassword(passwordGroup: AbstractControl) {

    const password = passwordGroup.get('password').value;

    const passwordConfirm = passwordGroup.get('passwordConfirm').value;

    if (password !== passwordConfirm) {
      return {checkPasswordConfirm: true};
    }
    return null;
  }

}
