import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MasterProductDetailsModel } from '../model/masterProductDetails.model';
import { BASE_URL } from '../util/const';

@Injectable({
  providedIn: 'root',
})
export class MasterProductDetailsService {

      constructor(private Http: HttpClient) {}

 
   
     public fetchAllProductDetailsList() { 
        return this.Http.get<MasterProductDetailsModel[]>(`${BASE_URL}masterProductDetails/findAllMasterProductDetails`);

    }



    public addProductDetails(productObject: MasterProductDetailsModel) {
        return this.Http.post<MasterProductDetailsModel>(`${BASE_URL}masterProductDetails/addMasterProductDetails`, productObject, {
            observe: 'response'
        });
    }

  

    public editProductDetails(productObject2: MasterProductDetailsModel) {

        return this.Http.put<MasterProductDetailsModel>(`${BASE_URL}masterProductDetails/updateMasterProductDetails`, productObject2, {
            observe: 'response'
        })
    }

    public deleteProductDetails(productId: number) {
        return this.Http.post<MasterProductDetailsModel>(`${BASE_URL}masterProductDetails/isDeleteMasterProductDetails/${productId}`, {
                    observe: 'response'
                })
          }
}