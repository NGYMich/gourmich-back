import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Recette} from "../model/recette";
import {HttpHeaders} from '@angular/common/http';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type': 'application/json',
    Authorization: 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class RecetteService {

  constructor(private http: HttpClient) {
  }

  rootURL = '/api';

  getRecettes() {
    return this.http.get(this.rootURL + '/recettes');
  }

  addRecette(recette: Recette) {
    const body = JSON.stringify(recette);
    const headers = {'content-type': 'application/json'}


    return this.http.post<Recette>(this.rootURL + '/recette', body, {'headers': headers}).subscribe(
      data => console.log('success', data),
      error => console.log('oops', error)
    );
  }

}
