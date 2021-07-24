import { Component, OnInit } from '@angular/core';
import {LoadingService} from "../../services/loading.service";
import {Observable} from "rxjs";

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.scss']
})
export class LoadingComponent implements OnInit {
  public showLoading = false;
  public checkLoading$: Observable<any> | undefined;

  constructor(private loading: LoadingService) { }

  ngOnInit(): void {
    this.checkLoading$ = this.loading.countOfFetch$;
    this.checkLoading$.subscribe(() => {
      this.showLoading = this.loading.isLoading();
    });
  }

}
