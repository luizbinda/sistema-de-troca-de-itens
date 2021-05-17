import { Component, AfterViewInit, ElementRef, Renderer2, ViewChild, OnDestroy, OnInit, NgZone } from '@angular/core';
import { ScrollPanel } from 'primeng';
import { MenusService, MenuOrientation } from '@nuvem/primeng-components';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html'
})
export class AppComponent implements OnInit {

    ngOnInit(){
    }

}
