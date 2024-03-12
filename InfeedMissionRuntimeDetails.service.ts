import { HttpClient } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { InfeedMissionRuntimeDetailsModel } from "../model/InfeedMissionRuntimeDetails.model";
import { BASE_URL } from "../util/const";
import { HttpHeaders } from '@angular/common/http';
import { HttpParams } from '@angular/common/http';

@Injectable({
    providedIn:"root"
})
export class InfeedMissionRuntimeDetailsService{
constructor(private http:HttpClient){

}

public fetchAllInfeedMissionRuntimeDetails(){
    return this.http.get<InfeedMissionRuntimeDetailsModel[]>(`${BASE_URL}infeedmissionruntimedetails/fetchAllByCurrentDate`);
}

fetchByInfeedMissionCdatetimeBetween(infeedMissionCdatetimeStart:String, infeedMissionCdatetimeEnd:String){
    return this.http.get<InfeedMissionRuntimeDetailsModel[]>(`${BASE_URL}fetchByInfeedMissionCdatetimeBetween/${infeedMissionCdatetimeStart}/${infeedMissionCdatetimeEnd}`);
}

public fetchInfeedMissionRuntimeDetailsByAllFilters(missionRuntimeInfeedStartDate:string, missionRuntimeInfeedStartTime:string,
    missionRuntimeInfeedEndDate:string, missionRuntimeInfeedEndTime:string, productName:string, selectedInfeedMissionStatus:string, 
    selectedskuCode:string, batchNumber:string, palletCode:string, selectedFloor:string, selectedArea: string){
        const url = `${BASE_URL}`+'infeedmissionruntimedetails/searchInfeedMissionRuntimeDetails';
        let queryParams = {"missionRuntimeInfeedStartDate":missionRuntimeInfeedStartDate,
                           "missionRuntimeInfeedStartTime":missionRuntimeInfeedStartTime,
                           "missionRuntimeInfeedEndDate":missionRuntimeInfeedEndDate,
                           "missionRuntimeInfeedEndTime":missionRuntimeInfeedEndTime,
                           "productName":productName,
                           "missionRuntimeInfeedStatus":selectedInfeedMissionStatus,
                           "skuCode":selectedskuCode,
                           "batchNo":batchNumber,
                           "palletCode":palletCode,
                           "floorName":selectedFloor,
                           "areaName":selectedArea};
        return this.http.get<InfeedMissionRuntimeDetailsModel[]>(url,{params:queryParams});
}


public updateInfeedMissionRuntimeDetailsDetails(infeedMissionObj: InfeedMissionRuntimeDetailsModel) {

    return this.http.put<InfeedMissionRuntimeDetailsModel[]>(`${BASE_URL}updateInfeedMissionRuntimeDetailsDetails`, infeedMissionObj)

}

public fetchInfeedMissionRuntimeDetails(){

    return this.http.get<InfeedMissionRuntimeDetailsModel[]>(`${BASE_URL}fetchInfeedMissionRuntimeDetails`);

}

public fetchFilledPalletCount(productVariantCode :string,palletStatusId : number){
    return this.http.get<InfeedMissionRuntimeDetailsModel[]>(`${BASE_URL}fetchFilledPalletCount/${productVariantCode}/${palletStatusId}`);
}

public fetchEmptyPalletCount(productVariantCode :string,palletStatusId : number){
    return this.http.get<InfeedMissionRuntimeDetailsModel[]>(`${BASE_URL}fetchEmptyPalletCount/${productVariantCode}/${palletStatusId}`);
}

}