import { Component } from '@angular/core';

@Component({
  selector: 'app-about-dev',
  templateUrl: './about-dev.component.html',
  styleUrls: ['./about-dev.component.css']
})
export class AboutDevComponent {

  ngOnInit(): void {
    document.body.style.backgroundColor='white'
  }
}
