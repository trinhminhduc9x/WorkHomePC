import {Component, OnInit} from '@angular/core';
import {Product} from '../../model/product';
import {Category} from '../../model/category';
import {User} from '../../model/User';

@Component({
  selector: 'app-list-ticket',
  templateUrl: './list-product.component.html',
  styleUrls: ['./list-product.component.css']
})
export class ListProductComponent implements OnInit {

  products: Product[];
  categories: Category[];
  users: User[];

  productDetail: Product | undefined;
  p = 1;
  count = 5;
  searchFromPlace = '';
  searchToPlace = '';
  searchStartDate = '2022-12-02';
  searchStartDate2 = '2023-01-02';

  constructor(private productService: ProductService) {
  }

  ngOnInit(): void {
    // this.resetData();
    this.getGarages();
    this.getUsers();
    // this.findNameEmailProductType();
    this.getAll();
  }

  findTwoField() {
    this.p = 1;
    this.productService.searchTwoField(this.searchFromPlace, this.searchToPlace).subscribe(data => {
      this.products = data;
    }, error => {
      this.productService.showErrorNotification('Không thể kết nối đến Server');
    });
  }

  getAll() {
    this.productService.findAll().subscribe(data => {
      this.products = data;
    }, error => {
      this.productService.showErrorNotification('Không thể kết nối đến Server');
    });
  }

  getGarages() {
    this.productService.findAllProductTypes().subscribe(
      data => {
        this.categories = data;
      }, error => {
        this.productService.showErrorNotification('Có lỗi khi tải Loại khách hàng!');
      }
    );
  }

  getUsers() {
    this.productService.findAllProductTypes().subscribe(
      data => {
        this.categories = data;
      }, error => {
        this.productService.showErrorNotification('Có lỗi khi tải Loại khách hàng!');
      }
    );
  }

  resetData() {
    this.searchFromPlace = '';
    this.searchToPlace = '';
    this.searchStartDate = '2022-12-02';
    this.searchStartDate2 = '2023-01-02';
  }

  search4Field() {
    this.p = 1;
    this.productService.searchTwoField(
      this.searchFromPlace, this.searchToPlace).subscribe(
      data => {
        this.products = data.filter(value => {
          const searchDate = new Date(value.startDate);
          const searchStart = new Date(this.searchStartDate);
          const searchEnd = new Date(this.searchStartDate2);
          if (searchDate >= searchStart && searchDate <= searchEnd) {
            return data;
          }
        });
      }
    );
  }

  buyConfirm(id: number) {
    this.productService.findById(id).subscribe(data => {
      this.productDetail = data;
    });
  }

}

