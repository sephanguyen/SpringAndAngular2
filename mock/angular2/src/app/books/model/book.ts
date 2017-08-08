
export class Book{

    constructor(public id: number, 
                public name: string,
                public author: string,
                public publish: string,
                public yearPublished: Date,
                public pageCount: number,
                public price: number,
                public dateArrived: Date,
                public borrowed: number,
                public rating: number,
                public active: boolean){};
}