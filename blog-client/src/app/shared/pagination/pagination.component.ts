import {Component, Input, Output, EventEmitter} from '@angular/core';

@Component({
  selector: 'app-pagination',
  templateUrl: './pagination.component.html'
})
export class PaginationComponent {
  @Input() offset: number = 0;
  @Input() limit: number = 0;
  @Input() totalResults: number = 0;
  @Output() onChange: EventEmitter<number> = new EventEmitter<number>();

  constructor() { }
}
