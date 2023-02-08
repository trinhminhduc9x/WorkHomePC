import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { PostRoutingModule } from './post-routing.module';
import { PostCreateComponent } from './post-create/post-create.component';
import { PostEditComponent } from './post-edit/post-edit.component';
import { PostListComponent } from './post-list/post-list.component';
import { PostDeleteComponent } from './post-delete/post-delete.component';
import { PostDetailComponent } from './post-detail/post-detail.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import { PostApprovalComponent } from './post-approval/post-approval.component';
import { PostListApprovalComponent } from './post-list-approval/post-list-approval.component';
import {ToastContainerModule} from 'ngx-toastr';

@NgModule({
  declarations: [PostCreateComponent, PostEditComponent, PostListComponent, PostDeleteComponent, PostDetailComponent,
    PostApprovalComponent, PostListApprovalComponent],
  exports: [
    PostDeleteComponent,
    PostApprovalComponent
  ]
  ,
    imports: [
        CommonModule,
        PostRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        ToastContainerModule
    ]
})
export class PostModule { }
