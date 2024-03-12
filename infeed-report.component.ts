import { formatDate } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { Workbook } from 'exceljs';
import * as  fileServer from 'file-saver'
import { InfeedMissionRuntimeDetailsModel } from 'src/app/model/InfeedMissionRuntimeDetails.model';
import { MasterAreaDetailsModel } from 'src/app/model/masterAreaDetails.model';
import { MasterFloorDetailsModel } from 'src/app/model/masterFloorDetails.model';
import { MasterProductDetailsModel } from 'src/app/model/masterProductDetails.model';
import { MasterProductVariantDetailsModel } from 'src/app/model/masterProductVariantDetails.model';
import { MasterShiftDetailsModel } from 'src/app/model/masterShiftDetails.model';
import { MasterSKUDetailsModel } from 'src/app/model/masterSKUDetails.model';
import { InfeedMissionRuntimeDetailsService } from 'src/app/services/InfeedMissionRuntimeDetails.service';
import { MasterAreaDetailsService } from 'src/app/services/masterAreaDetails.service';
import { MasterFloorDetailsService } from 'src/app/services/masterFloorDetails.service';
import { MasterProductDetailsService } from 'src/app/services/masterProductDetails.service';
import { MasterSKUDetailsService } from 'src/app/services/masterSKUDetails.service';

@Component({
  selector: 'app-infeed-report',
  templateUrl: './infeed-report.component.html',
  styleUrls: ['./infeed-report.component.css']
})
export class InfeedMissionRuntimeDetailsComponent implements OnInit {
  infeedMissionReportDtOptions: DataTables.Settings = {};
  infeedMissionRuntimeDetailsList: InfeedMissionRuntimeDetailsModel[] = [];

  loadingSideInfeedMissionList: InfeedMissionRuntimeDetailsModel[] = [];
  
  //Fetching ProductName
  productNameDropDownList: MasterProductDetailsModel[] = [];
  selectedProductName!: string;

  //Fetching Floor Name
  floorDropDownList: MasterFloorDetailsModel[] = [];
  selectedFloor!: string;

  //Fetching Floor Name
  areaDropDownList: MasterAreaDetailsModel[] = [];
  selectedArea!: string;

  skuCode!: string;
  batchNumber!: string;
  floorName!: string;
  areaName!: string;
  palletCode!: string;
  selectedInfeedMissionStatus!: string
  infeedMissionCdatetimeStart!: string
  infeedMissionCdatetimeEnd!: string
  disableSearchButton: boolean = false;
  disableDateTime: boolean = true;
  missionRuntimeInfeedStartDate!: string;
  missionRuntimeInfeedStatus!: string;
  missionRuntimeInfeedEndDate!: string;
  missionRuntimeInfeedStartTime!: string;
  missionRuntimeInfeedEndTime!: string;



  constructor(private infeedMissionRuntimeDetailsService: InfeedMissionRuntimeDetailsService,
    //Fetching ProductName
    private masterProductDetailsService: MasterProductDetailsService,
    //Fetching skuCode
    private masterSKUDetailsService: MasterSKUDetailsService,
    //Fetching floor
    private masterFloorDetailsService: MasterFloorDetailsService,
    //Fetching area
    private masterAreaDetailsService: MasterAreaDetailsService
  ) { }

  ngOnInit(): void {
    //On refresh or on opning of this page following data will be fetched 
    // this.fetchAllInfeedRunTimeReportByCurrentDate();
    this.fetchProductNameFromProductDetails();
    this.fetchfloorDetails();
    this.fetchAreaDetails();


    this.infeedMissionReportDtOptions = {
      // pagingType: 'full_numbers',
      // pageLength: 10,
      // serverSide: true,
      // processing: true,
      ajax: () => {
        this.fetchAllInfeedRunTimeReportByCurrentDate();
    }}
  }

  fetchAllInfeedRunTimeReportByCurrentDate() {
    this.disableDateTime = true;
    this.disableSearchButton = false;
    this.infeedMissionRuntimeDetailsService.fetchAllInfeedMissionRuntimeDetails().subscribe(
      infeedDetailsList => {
        $('#infeedMissionRuntimeDetailsId').DataTable().clear().destroy();

        this.infeedMissionRuntimeDetailsList = infeedDetailsList;

        this.loadingSideInfeedMissionList = this.infeedMissionRuntimeDetailsList.filter(loadedList => loadedList.palletStatusId == 1);

        $(function () {
          $("#infeedMissionRuntimeDetailsId").DataTable();
        });
        
      }     
    )
  }


  public fetchInfeedMissionRuntimeDetailsByAllFilters() {
    this.resetData();
    this.infeedMissionRuntimeDetailsService.fetchInfeedMissionRuntimeDetailsByAllFilters(this.missionRuntimeInfeedStartDate,
      this.missionRuntimeInfeedStartTime, this.missionRuntimeInfeedEndDate, this.missionRuntimeInfeedEndTime, this.selectedProductName,
      this.selectedInfeedMissionStatus, this.skuCode, this.batchNumber, this.palletCode, this.selectedFloor,
      this.selectedArea).subscribe(
        infeedMissionRuntimeDetailsFilter => {
          $('#infeedMissionRuntimeDetailsId').DataTable().clear().destroy();
          this.infeedMissionRuntimeDetailsList = infeedMissionRuntimeDetailsFilter;

          this.loadingSideInfeedMissionList = this.infeedMissionRuntimeDetailsList.filter(loadedList => loadedList.palletStatusId == 1);

          $(function () {
            $("#infeedMissionRuntimeDetailsId").DataTable();
          });
        }
      )
  }

    
  //Fetching ProductName
  public fetchProductNameFromProductDetails() {
    this.masterProductDetailsService.fetchAllProductDetails().subscribe(
      productList => {
        this.productNameDropDownList = productList;
      });
  }
  public selectProductNameChangeHandler(value: string) {
    this.selectedProductName = value;
  }
  
  //Fetching ProductVarientCode
  public fetchfloorDetails() {
    this.masterFloorDetailsService.fetchAllMasterFloorDetails().subscribe(
      floorList => {
        this.floorDropDownList = floorList;
      });
  }
  public selectFloorChangeHandler(value: string) {
    this.selectedFloor = value;
  }

  //Fetching ProductVarientCode
  public fetchAreaDetails() {
    this.masterAreaDetailsService.fetchAllMasterAreaDetails().subscribe(
      areaList => {
        this.areaDropDownList = areaList;
      });
  }
  public selectAreaChangeHandler(value: string) {
    this.selectedArea = value;
  }

  selectInfeedMissionStatusChangeHandler(value: string) {
    this.selectedInfeedMissionStatus = ''
    this.selectedInfeedMissionStatus = value

  }


  public dateTimeValidation() {

    if (this.missionRuntimeInfeedStartDate != null && (this.missionRuntimeInfeedEndDate == null || this.missionRuntimeInfeedEndDate == 'NA')) {
      this.disableDateTime = false;
      this.disableSearchButton = true;
    }

    else if (this.missionRuntimeInfeedStartDate != null && this.missionRuntimeInfeedEndDate != null && this.missionRuntimeInfeedStartDate <= this.missionRuntimeInfeedEndDate) {
      this.disableSearchButton = false;
    }

    else if (this.missionRuntimeInfeedStartDate != null && this.missionRuntimeInfeedEndDate != null && this.missionRuntimeInfeedStartDate < this.missionRuntimeInfeedEndDate) {
      this.disableSearchButton = false;
    }


    else if (this.missionRuntimeInfeedStartDate == this.missionRuntimeInfeedEndDate) {
      this.disableSearchButton = false;
    }

    else {
      this.disableSearchButton = true;
    }
  }

  public timeValidation() {

    if (this.missionRuntimeInfeedStartTime != null && (this.missionRuntimeInfeedEndTime == null || this.missionRuntimeInfeedEndTime == 'NA')) {
      this.disableDateTime = false;
      this.disableSearchButton = true;
    }

    else if (this.missionRuntimeInfeedStartTime != null && this.missionRuntimeInfeedEndTime != null && this.missionRuntimeInfeedStartTime <= this.missionRuntimeInfeedEndTime) {
      this.disableSearchButton = false;
    }

    else if (this.missionRuntimeInfeedStartTime != null && this.missionRuntimeInfeedEndTime != null && this.missionRuntimeInfeedStartTime < this.missionRuntimeInfeedEndTime) {
      this.disableSearchButton = false;
    }


    else if (this.missionRuntimeInfeedStartTime == this.missionRuntimeInfeedEndTime) {
      this.disableSearchButton = false;
    }

    else {
      this.disableSearchButton = true;
    }
  }


  resetData() {
    if (this.missionRuntimeInfeedStartDate == undefined || this.missionRuntimeInfeedStartDate == null) {
      this.missionRuntimeInfeedStartDate = ""
    }
    if (this.missionRuntimeInfeedStartTime == undefined || this.missionRuntimeInfeedStartTime == null) {
      this.missionRuntimeInfeedStartTime = ""
    }
    if (this.missionRuntimeInfeedEndDate == undefined || this.missionRuntimeInfeedEndDate == null) {
      this.missionRuntimeInfeedEndDate = ""
    }
    if (this.missionRuntimeInfeedEndTime == undefined || this.missionRuntimeInfeedEndTime == null) {
      this.missionRuntimeInfeedEndTime = ""
    }
    if (this.selectedProductName == undefined || this.selectedProductName == null) {
      this.selectedProductName = ""
    }
    if (this.selectedInfeedMissionStatus == undefined || this.selectedInfeedMissionStatus == null) {
      this.selectedInfeedMissionStatus = ""
    }
    if (this.skuCode == undefined || this.skuCode == null || this.skuCode.trim() == "") {
      this.skuCode = ""
    }
    if (this.batchNumber == undefined || this.batchNumber == null || this.batchNumber.trim() == "") {
      this.batchNumber = ""
    }
    if (this.palletCode == undefined || this.palletCode == null || this.palletCode.trim() == "") {
      this.palletCode = ""
    }
    
    if (this.selectedFloor == undefined || this.selectedFloor == null) {
      this.selectedFloor = ""
    }
    if (this.selectedArea == undefined || this.selectedArea == null) {
      this.selectedArea = ""
    }
            
  }
  
    generateInfeedRunTimeExcelReport() {
 
   
    if (this.infeedMissionRuntimeDetailsList.length > 0) {


      //  const logoBase64Logo = "";
      const headerRowsCount = 6;

   
  const title = 'INFEED MISSION DETAILS REPORT'+"    " +formatDate(new Date(), 'dd-MMM-yyyy HH:mm:ss', 'en-US');

   

      const header = ["Sr.No", "Mission", "Pallet Code", "Sku Code", "Sku Name", "Product Name", "PO No.", "Batch No", "Quantity", "Floor Name", "Area Name", "Rack Name", "Position Id", "Status", "Start Date", "End Date", "Start Time", "End Time"];


      // Convert the id to sr.no
      for (let i = 0; i < this.infeedMissionRuntimeDetailsList.length; i++) {
        this.infeedMissionRuntimeDetailsList[i].infeedMissionId = (i + 1)
      }

      const data = this.infeedMissionRuntimeDetailsList.map((obj) =>
        Object.values({
          infeedMissionId:obj.infeedMissionId,
          missionRouteId: obj.missionRouteId,
          palletCode: obj.palletCode,
          skuCode: obj.skuCode,
          skuName: obj.skuName,
          productName: obj.productName,
          prodOrderNo: obj.prodOrderNo,
          batchNo: obj.batchNo,
          quantity: obj.quantity,
          floorName: obj.floorName,
          // weight: obj.weight,
          areaName: obj.areaName,
          rackName: obj.rackName,
          positionId: obj.positionId,
          missionRuntimeInfeedStatus: obj.missionRuntimeInfeedStatus,
          missionRuntimeInfeedStartDate: obj.missionRuntimeInfeedStartDate,
          missionRuntimeInfeedEndDate: obj.missionRuntimeInfeedEndDate,
          missionRuntimeInfeedStartTime: obj.missionRuntimeInfeedStartTime,
          missionRuntimeInfeedEndTime: obj.missionRuntimeInfeedEndTime

        }
        )
      );
      
      let workbook = new Workbook();
      let worksheet = workbook.addWorksheet('Infeed Mission Data');

      // Add new row
      let titleRow = worksheet.addRow([title]);
      var titleRow1 = worksheet.addRow([]);

      var titleRow2 = worksheet.addRow([]);

      // Set font, size and style in title row.
      // titleRow.font = { name: 'Calibri', family: 4, size: 22, underline: 'double', bold: true };
      titleRow.font = { name: 'Calibri', family: 4, size: 22 };

      titleRow1.font = {name: 'Calibri', family: 4, size: 16};

      titleRow2.font = {name: 'Calibri', family: 4, size: 16};


      // Align the title in the center
      worksheet.getCell('A1').alignment = { vertical: 'middle', horizontal: 'center' };
      worksheet.getCell('D2').alignment = { vertical: 'middle', horizontal: 'center' };
      worksheet.getCell('D3').alignment = { vertical: 'middle', horizontal: 'center' };
      worksheet.getCell('H2').alignment = { vertical: 'middle', horizontal: 'center' };
      worksheet.getCell('H3').alignment = { vertical: 'middle', horizontal: 'center' };
      worksheet.getCell('L2').alignment = { vertical: 'middle', horizontal: 'center' };
      worksheet.getCell('L3').alignment = { vertical: 'middle', horizontal: 'center' };
      worksheet.getCell('P2').alignment = { vertical: 'middle', horizontal: 'center' };
      worksheet.getCell('P3').alignment = { vertical: 'middle', horizontal: 'center' };

      //Merge Cells
      worksheet.mergeCells(`A${titleRow.number}:R${titleRow.number}`);
      
      // Blank Row
      worksheet.addRow([]);
           
    //Add Header Row
    let headerRow = worksheet.addRow(header);
    // Cell Style : Fill and Border
    headerRow.eachCell((cell, number) => {
      cell.fill = {
        type: 'pattern',
        pattern: 'solid',
        fgColor: { argb: '4472C4' },
         
      }
      cell.font = {
   
        color: { argb: 'FFFFFF' },
        size:12,
        bold:true,       
      }
      cell.border = { top: { style: 'thin' }, left: { style: 'thin' }, bottom: { style: 'thin' }, right: { style: 'thin' } }
    });


      // Add all data without formatting
      worksheet.addRows(data);

      
      // To give the width to the column
      worksheet.getColumn(2).width = 15;
      worksheet.getColumn(3).width = 15;
      worksheet.getColumn(4).width = 20;
      worksheet.getColumn(5).width = 20;
      worksheet.getColumn(6).width = 15;
      worksheet.getColumn(7).width = 15;
      worksheet.getColumn(8).width = 15;
      worksheet.getColumn(9).width = 15;
      worksheet.getColumn(10).width = 15;
      worksheet.getColumn(11).width = 15;
      worksheet.getColumn(12).width = 15;
      worksheet.getColumn(13).width = 15;
      worksheet.getColumn(14).width = 15;
      worksheet.getColumn(15).width = 15;
      worksheet.getColumn(16).width = 15;
      worksheet.getColumn(17).width = 30;
      worksheet.getColumn(18).width = 30;


      // worksheet.addRow([]);

      //Footer Row
      let footerRow = worksheet.addRow(['This is system generated excel sheet.']);
      footerRow.getCell(1).fill = {
        type: 'pattern',
        pattern: 'solid',
        fgColor: { argb: 'F1F5F9' }
      };
      footerRow.getCell(1).border = { top: { style: 'thin' }, left: { style: 'thin' }, bottom: { style: 'thin' }, right: { style: 'thin' } }
      // Align the footer in the center
      worksheet.getCell('A' + (data.length + headerRowsCount + 1)).alignment = { vertical: 'middle', horizontal: 'center' };
      // console.log(data.length + headerRowsCount + 1);

      //Merge Cells
      worksheet.mergeCells(`A${footerRow.number}:S${footerRow.number}`);

      // Save the file in Excel format
      workbook.xlsx.writeBuffer().then((data) => {
        let blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });

        // const fileName="ProdData"+(new Date().getDay())+(new Date().getMonth())+(new Date().getFullYear())+(new Date().getTime())+(new Date().getMinutes())+(new Date().getSeconds());


        const todayDate = new Date();
        console.log(todayDate);

        const fileName = "InfeedReport_" + (todayDate.getDate()) + (todayDate.getMonth() + 1) + (todayDate.getFullYear()) + (todayDate.getHours())
          + (todayDate.getMinutes()) + (todayDate.getSeconds());

        fileServer.saveAs(blob, fileName + '.xlsx');
      })



    }
    else {
      alert("Data is not available");
    }
  }

}




