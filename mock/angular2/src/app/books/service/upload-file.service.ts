import { Injectable } from '@angular/core';
import {Observable} from 'rxjs/Rx';

@Injectable()
export class UploadFileService {
  progress$: any;
  progress: any;
  progressObserver: any;
  private url: string = "books/upload";
   constructor () {
    this.progress$ = Observable.create(observer => {
      this.progressObserver = observer
    }).share();
  }

  public makeFileRequest ( files: File[]): Observable<any> {
    return Observable.create(observer => {
      let formData: FormData = new FormData(),
        xhr: XMLHttpRequest = new XMLHttpRequest();

      for (let i = 0; i < files.length; i++) {
        formData.append("uploads[]", files[i], files[i].name);
      }

      xhr.onreadystatechange = () => {
        if (xhr.readyState === 4) {
          if (xhr.status === 200) {
            observer.next(JSON.parse(xhr.response));
            observer.complete();
          } else {
            observer.error(xhr.response);
          }
        }
      };

      xhr.upload.onprogress = (event) => {
        this.progress = Math.round(event.loaded / event.total * 100);

        this.progressObserver.next(this.progress);
      };

      xhr.open('POST', this.url, true);
      xhr.send(formData);
    });
  }

}
