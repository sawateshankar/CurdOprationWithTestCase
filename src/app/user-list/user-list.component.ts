import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { UserService } from '../user.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-list',
  templateUrl: './user-list.component.html',
  styleUrl: './user-list.component.css'
})
export class UserListComponent implements OnInit {
 
  users:User[];
  constructor(private userService:UserService,private router:Router){
    this.users = [];
  }


  ngOnInit(): void {

    this.getUser();
  }

  private getUser(): void {
    this.userService.getUserList().subscribe(data => {
      this.users = data; // Assign the received data to the users array
    });
  }

  userUpdate(id: number) {
  this.router.navigate(['update-user',id]);
}
userDelete(id: number) {

    this.userService.deleteUser(id).subscribe(
     data=>{
      console.log(data);
      this.getUser();
     }
    );
  }
  userDetailView(id:number){
    this.router.navigate(['user-details',id]);
  }


}
