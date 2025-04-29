'use client'
import {DataTable,Table,TableHead,TableRow,TableBody} from "@/components/search/Table"
import dummyData from "@/components/search/dummy";
import useDialog from "@/hooks/useDialog";
import Dialog from "@/components/common/Dialog"
export default function Page() {
   const {isOpen,openDialog,closeDialog} = useDialog()
   console.log(isOpen)
  return (
     <>
         <Dialog.OpenTrigger 
          onClick={openDialog} 
          className="w-10 h-6 flex items-center justify-center rounded-[8px] bg-gray-300 cursor-pointer text-white text-xs">
          추가
        </Dialog.OpenTrigger>

        <Dialog.DialogContent
         isOpen={isOpen}
         closeDialog={closeDialog}
         >
         <div className="z-2000 w-[36rem] h-[24rem] min-w-3/4 bg-white">content</div>
        </Dialog.DialogContent>
        <DataTable
            className="text-[14px]"
            data={dummyData}
      />
     </>
  );
}
