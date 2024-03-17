import { Component, OnInit } from '@angular/core';
import { User } from '../user';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';

@Component({
  selector: 'app-user-details',
  templateUrl: './user-details.component.html',
  styleUrl: './user-details.component.css'
})
export class UserDetailsComponent implements OnInit{


  id:number;
  user:User=new User();
constructor(private route:ActivatedRoute,private userService:UserService){
this.id=0;

}

  ngOnInit(): void {
  this.id = +this.route.snapshot.params['id']; 
  this.userService.getUserById(this.id).subscribe(
    (data: User) => {
      this.user = data;
    },
    (error) => {
      console.error('Error fetching user data:', error);

    });
  }

}
