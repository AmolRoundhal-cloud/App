import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MasterProductDetailsModel } from 'src/app/model/masterProductDetails.model';
import { AuthenticationService } from 'src/app/services/auth.service';
import { MasterProductDetailsService } from 'src/app/services/masterProductDetails.service';

@Component({
  selector: 'app-master-product-details',
  templateUrl: './master-product-details.component.html',
  styleUrls: ['./master-product-details.component.css']
})

export class MasterProductDetailsComponent implements OnInit {
  productDtOption: DataTables.Settings = {};
  password!: string

  masterProductDetailsTableList: MasterProductDetailsModel[] = [];


  deleteProductDetailsForSelectedIdInstance: MasterProductDetailsModel = new MasterProductDetailsModel();

  editProductDetailsForSelectedIdInstance: MasterProductDetailsModel = new MasterProductDetailsModel();


  addProductDetailsInstance: MasterProductDetailsModel = new MasterProductDetailsModel();


  constructor(private masterProductDetailsService: MasterProductDetailsService, private authservice: AuthenticationService) { }

  ngOnInit(): void {
  
    // this.productDtOption = {
    //   pagingType: 'full_numbers'
    // };


    this.productDtOption = {
      //pagingType: 'full_numbers',
      ajax: () => {
        this.fetchAllMasterProductDetails();
      }
    }
  }

  show: boolean = false;

  // click event function toggle
  passwordShow() {
    this.show = !this.show;
  }

  deleteMasterProductDetails() {
     this.masterProductDetailsService.deleteProductDetails(this.deleteProductDetailsForSelectedIdInstance.productId).subscribe(
        deleteProductDetail => {
          this.fetchAllMasterProductDetails();
          $(".modal-backdrop").remove();
          /*if (deleteProductDetail.status == 200) {
            alert("Product Details Deleted Successfully")
            this.fetchAllMasterProductDetails();
          }
          else {
            alert("Product Details Not Found")
          }*/
      }
    )
 }

  editMasterProductDetails(){

    this.masterProductDetailsService.editProductDetails(this.editProductDetailsForSelectedIdInstance).subscribe(
      editNewProductDetails => {

        if (editNewProductDetails.status == 200) {
          alert("Product Details Updated Successfully");
          this.fetchAllMasterProductDetails();
        }
        else {
          alert("Product Details Already Exist");
        }
      }
    )

  }
  checkLoginUserPassword(passwordCheckForm: NgForm, pass: string) {

    this.password = pass;

    this.authservice.checkLoginUserPassword(this.authservice.currentUserValue.userName, this.password).subscribe(userData => {

      if (userData.userName != null && userData.userId == this.authservice.currentUserValue.userId) {

        // this.masterAgeingDaysDetailsService.updateAgeingDaysDetails(this.editAgeingDaysDetailsInstance).subscribe(ageingDaysDetailsList => {
        //   alert("Ageing Days Updated Successfully");
        //   this.fetchAgeingDaysDetails();                  
        // });
        // this.editProductDetailsForSelectedIdInstance.productCreatedByUser = this.authservice.currentUserValue.userId;
        // this.editProductDetailsForSelectedIdInstance.productCreatedByUserName = this.authservice.currentUserValue.userName;
        this.masterProductDetailsService.editProductDetails(this.editProductDetailsForSelectedIdInstance).subscribe(
          editNewProductDetails => {

            if (editNewProductDetails.status == 200) {
              alert("Product Details Updated Successfully");
              this.fetchAllMasterProductDetails();
            }
            else {
              alert("Product Details Already Exist");
            }
          }
        )
      }
      else {
        alert("Please Enter correct Password");
      }
    });
    passwordCheckForm.reset();

  }


  checkLoginUserPasswordForDelete(passwordCheckForm: NgForm, pass: string) {

    this.password = pass;

    this.authservice.checkLoginUserPassword(this.authservice.currentUserValue.userName, this.password).subscribe(userData => {

      if (userData.userName != null && userData.userId == this.authservice.currentUserValue.userId) {
        console.log("1");

  
       // this.masterProductDetailsService.deleteProductDetails(this.deleteProductDetailsForSelectedIdInstance.productId, this.authservice.currentUserValue.userId,
        //   this.authservice.currentUserValue.userName).subscribe(
       //   deleteProductDetail => {
      //      this.fetchAllMasterProductDetails();
       //     console.log("2");
       //     $(".modal-backdrop").remove();
    
            // if (deleteProductDetail.status == 200) {
            //   alert("Product Details Deleted Successfully")
            // }
            // else {
            //   alert("Product Details Not Found")
            // }
        //  }
     //   );
      }
      else {
        alert("Please Enter correct Password");
      }
    });
    passwordCheckForm.reset();

  }


  public fetchAllMasterProductDetails() {
  this.masterProductDetailsService.fetchAllProductDetailsList().subscribe(masterDetailsList =>{
  $('#prodId').DataTable().clear().destroy();
  $(function(){
    $("#prodId").DataTable();
   });
  this.masterProductDetailsTableList=masterDetailsList;
 
  console.log("masterList"+this.masterProductDetailsTableList.length);
})
  }




  public addMasterProductDetails(addProductDetailsModalForm: NgForm) {

    const user = this.authservice.currentUserValue;
        
    this.masterProductDetailsService.addProductDetails(this.addProductDetailsInstance).subscribe(
      addNewProductDetails => {

        this.fetchAllMasterProductDetails();
        if (addNewProductDetails.status == 200) {
          alert("Product Details Added Successfully");
        }
        else {
          alert("Product Details Already Exists");
        }
      }
    )
    addProductDetailsModalForm.reset();
  }


  


  public deleteMasterProductDetailsRow(deleteProductDetailsRowInstance: MasterProductDetailsModel) {
    this.deleteProductDetailsForSelectedIdInstance = Object.assign({}, deleteProductDetailsRowInstance);
    this.password = '';
  }


  // public editMasterProductDetails() {
  //   console.log("123");
  //   // this.editProductDetailsForSelectedIdInstance.productCreatedByUser = this.authservice.currentUserValue.userId;
  //   // this.editProductDetailsForSelectedIdInstance.productCreatedByUserName = this.authservice.currentUserValue.userName;
  //   // this.masterProductDetailsService.editProductDetails(this.editProductDetailsForSelectedIdInstance).subscribe(
  //   //   editNewProductDetails => {

  //   //     if (editNewProductDetails.status == 200) {
  //   //       alert("Product Details Updated Successfully");
  //   //       this.fetchAllMasterProductDetails();
  //   //     }
  //   //     else {
  //   //       alert("Product Details Already Exist");
  //   //     }
  //   //   }
  //   // )
  // }


  public editMasterProductDetailsRow(editProductDetailsRowInstance: MasterProductDetailsModel) {
    // assigning object to another object.as curly '{}' braces are important
    //this.editProductDetailsForSelectedIdInstance = Object.assign({}, editProductDetailsRowInstance);
  }

  public editMasterProductRow(editAgeingDaysDetailsInstance1: MasterProductDetailsModel) {
    this.editProductDetailsForSelectedIdInstance = Object.assign({}, editAgeingDaysDetailsInstance1);
    this.password = '';

  }

  

}