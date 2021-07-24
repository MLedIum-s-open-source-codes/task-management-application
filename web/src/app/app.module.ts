import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import {RouterModule, Routes} from "@angular/router";
import { LoginComponent } from './pages/login/login.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';
import { RegisterComponent } from './pages/register/register.component';
import { LandingComponent } from './pages/landing/landing.component';
import { AdminComponent } from './pages/admin/admin.component';
import { MainComponent } from './pages/main/main.component';
import { LoadingComponent } from './components/loading/loading.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {InterceptorService} from "./services/interceptor.service";
import {AuthService} from "./services/auth.service";
import {LoadingService} from "./services/loading.service";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UserService} from "./services/user.service";
import { MainSidebarComponent } from './components/main-sidebar/main-sidebar.component';
import { DeskComponent } from './components/desk/desk.component';
import { TaskComponent } from './components/task/task.component';
import { ModalTaskComponent } from './components/modal-task/modal-task.component';


const routes: Routes = [
  {path: 'not-found', component: NotFoundComponent},
  {path: '', component: LandingComponent},
  {path: 'login', component: LoginComponent},
  {path: 'register', component: RegisterComponent},
  {path: 'main', component: MainComponent},
  {path: 'admin', component: AdminComponent},
  {path: '**', redirectTo: 'not-found'}
];

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    NotFoundComponent,
    RegisterComponent,
    LandingComponent,
    AdminComponent,
    MainComponent,
    LoadingComponent,
    MainSidebarComponent,
    DeskComponent,
    TaskComponent,
    ModalTaskComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    RouterModule.forRoot(routes),
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterceptorService,
      multi: true
    },
    AuthService,
    LoadingService,
    UserService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
