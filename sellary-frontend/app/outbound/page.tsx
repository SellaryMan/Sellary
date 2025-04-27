import {DataTable,Table,TableHead,TableRow,TableBody} from "@/components/search/ProductTable"
import dummyData from "@/components/search/dummy";
export default function Page() {

  return (
     <>
        <DataTable
            className="text-[14px]"
            data={dummyData}
      />
     </>
  );
}
