import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {ListProductComponent} from './product/list/list-product.component';
// import {CreateTicketComponent} from './product/create/create-ticket.component';


const routes: Routes = [{
  path: '',
  component: ListProductComponent
}
  ,
  // {
  //   path: 'tickets/create',
  //   component: CreateTicketComponent
  // }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
