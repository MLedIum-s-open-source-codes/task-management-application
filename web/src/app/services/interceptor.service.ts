import { Injectable } from '@angular/core';
import {
  HttpErrorResponse,
  HttpEvent,
  HttpHandler,
  HttpInterceptor,
  HttpRequest,
  HttpResponse
} from "@angular/common/http";
import {Observable} from "rxjs";
import {tap} from "rxjs/operators";
import {LoadingService} from "./loading.service";

@Injectable({
  providedIn: 'root'
})
export class InterceptorService implements HttpInterceptor {

  constructor(private loading: LoadingService) {}

  intercept(
    req: HttpRequest<any>,
    next: HttpHandler
  ): Observable<HttpEvent<any>> {

    const baseURL = 'http://localhost:8080/';

    console.log(req.url);

    const reqClone = req.clone({url: baseURL + req.url});
    this.loading.addFetch();
    return next.handle(reqClone).pipe(
      tap(
        (event) => {
          if (event instanceof HttpResponse){
            console.log('Server response');
            // console.log('event', event);
            this.loading.removeFetch();
          }
        },
        (err) => {
          if (err instanceof HttpErrorResponse) {
            // console.log('error', err);
            alert(err.error.message);
            this.loading.removeFetch();
          }
        }
      )
    );
  }
}
