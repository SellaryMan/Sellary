import { DataTable } from "@/components/search/Table";
import dummyData from "@/components/search/dummy";
export default function Page() {
  return (
     <>
     <DataTable
            className="text-[14px]"
            data={dummyData}/>
     </>
  );
}
