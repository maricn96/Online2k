
export class Food {

    id: string;
    name: string;
	calories: number;
    proteins: number;
    carbs: number;
    fat: number;

    constructor(obj?) {
        this.id = obj ? obj.id : "";
        this.name = obj ? obj.name : "";
        this.calories = obj ? obj.calories : "";
        this.proteins = obj ? obj.proteins : "";
        this.carbs = obj ? obj.carbs : "";
        this.fat = obj ? obj.fat : ""
    }

    deserialize(input: any) {
        Object.assign(this, input);
        return this;
    }


}
