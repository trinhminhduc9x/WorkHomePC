import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {HomeModule} from './home/home.module';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ToastrModule} from 'ngx-toastr';
import {HttpClientModule} from '@angular/common/http';
import {ReactiveFormsModule} from '@angular/forms';
import {PostModule} from './post/post.module';
import {environment} from '../environments/environment';
import {AngularFireModule} from '@angular/fire';
import {FormsModule} from '@angular/forms';
import {NgxPaginationModule} from 'ngx-pagination';
import {CKEditorModule} from '@ckeditor/ckeditor5-angular';


@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HomeModule,
    NgxPaginationModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot(),
    ReactiveFormsModule,
    PostModule,
    AngularFireModule.initializeApp(environment.firebaseConfig),
    FormsModule,
    CKEditorModule


  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
