import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Technician } from '../models/technician.model';

@Injectable({
  providedIn: 'root'
})
export class TechnicianService {

  private apiUrl = 'http://localhost:8081/api/technicians';

  constructor(private http: HttpClient) { }

  getTechnicians(): Observable<Technician[]> {
    return this.http.get<Technician[]>(this.apiUrl);
  }
}
