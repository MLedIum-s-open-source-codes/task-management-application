import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class LoadingService {
  public showLoading = false;
  public countOfFetch$: BehaviorSubject<any> = new BehaviorSubject<any>(0);

  constructor() { }

  addFetch(): void {
    this.countOfFetch$.next(this.countOfFetch$.value + 1);
  }

  removeFetch(): void {
    this.countOfFetch$.next(this.countOfFetch$.value - 1);
  }

  isLoading(): boolean {
    return this.countOfFetch$.value > 0;
  }

}
