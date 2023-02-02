import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {CustomerEditComponent} from './customer-edit/customer-edit.component';
import {CustomerCreateComponent} from './customer-create/customer-create.component';

const routes: Routes = [

  {
    path: '', component: CustomerCreateComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CustomerRoutingModule {
}
